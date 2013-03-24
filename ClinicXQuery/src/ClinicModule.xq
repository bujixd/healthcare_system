module namespace clin = 'http://www.example.com/ClinicModule';

import schema namespace c = "http://www.example.org/shema/clinic" at "Clinic.xsd";

import schema namespace p = "http://www.example.org/schema/clinic/patient" at "Patient.xsd";

import schema namespace t = "http://www.example.org/schema/clinic/treament" at "Treament.xsd";

import schema namespace prov = "http://www.example.org/schema/clinic/provider" at "Provider.xsd";

declare namespace f = "http://www.example.org/schema/clinic/function";

declare function clin:getPatientTreatments($kinic as element (c:Clinic),$patientID as xs:long )
as element(p:treatment){
        for $x in $kinic/p:Patient
        where $x/p:patient-id = $patientID
        return $x//p:treatment
};

declare function clin:getPatientDrugs($kinic as element (c:Clinic),$patientID as xs:long )
as element()*{
        for $x in $kinic/p:Patient
                  where $x/p:patient-id = $patientID 
                    for $t in $x/p:Treatment/p:treatment
                        for $m in $t/t:drug-treatment
                     return
                     <treatment>
                            <diagnosis>{$t/t:diagnosis/text()}</diagnosis>
                            <drug-name>{$m/t:name/text()}</drug-name>
                            <dosage>{$m/t:dosage/text()}</dosage>
                     </treatment>
                    
                        
                   };

declare function clin:getTreatmentInfo($kinic as element (c:Clinic))
as element(f:treatment-info){
        <f:treatment-info>{for $x in $kinic/p:Patient
                            for $t in $x/p:Treatment/p:treatment
                            where count($t/t:drug-treatment)>0
                               
                               return
                         <f:treatment-drug>
                                <f:patient-type>drug-treatment</f:patient-type>
                                <f:patient-id>{$x/p:patient-id/text()}</f:patient-id>
                                <f:provider-id>{$t/t:provide-id/text()}</f:provider-id>
                                <f:diagnosis>{$t/t:diagnosis/text()}</f:diagnosis>
                                <f:drug-info>{
                                for $m in $t/t:drug-treatment
                                return $m
                                }</f:drug-info>
                         </f:treatment-drug>,
       
        
                                for $x in $kinic/p:Patient
                                    for $t in $x/p:Treatment/p:treatment
                                    where count($t/t:radiology)>0
                                        return
                          <f:treatment-radiology>           
                                <f:patient-type>radiology-treatment</f:patient-type>
                                <f:patient-id>{$x/p:patient-id/text()}</f:patient-id>
                                <f:provider-id>{$t/t:provide-id/text()}</f:provider-id>
                                <f:diagnosis>{$t/t:diagnosis/text()}</f:diagnosis>
                                <f:radiology-info>{
                                for $m in $t/t:radiology
                                return $m
                                }</f:radiology-info>
                          </f:treatment-radiology>,
                     
                                for $x in $kinic/p:Patient
                                    for $t in $x/p:Treatment/p:treatment
                                    where count($t/t:surgery)>0
                                        
                                        return
                          <f:treatment-surgery>         
                                <f:patient-type>surgery-treatment</f:patient-type>    
                                <f:patient-id>{$x/p:patient-id/text()}</f:patient-id>
                                <f:provider-id>{$t/t:provide-id/text()}</f:provider-id>
                                <f:diagnosis>{$t/t:diagnosis/text()}</f:diagnosis>
                                <f:surgery-info>{
                                for $m in $t/t:surgery
                                return $m
                                }</f:surgery-info>
                          </f:treatment-surgery>
                }         
        </f:treatment-info>};
        
declare function clin:getProviderInfo($kinic as element(c:Clinic))
as element(f:provider-infos){
    <f:provider-infos>{
        for $p in $kinic/prov:Provider
        return 
        <f:providerInfo>{
            <f:provider-id>{$p/prov:provider-id/text()}</f:provider-id>,
            <f:provider-name>{$p/prov:name/text()}</f:provider-name>,
            <f:specialization>{$p/prov:specialzation/text()}</f:specialization>,
            <f:patients>
            {
                for $x in $kinic/p:Patient
                where $x/p:Treatment/p:treatment/t:provide-id=$p/prov:provider-id
                return <f:patient>{
                    <f:patient-id>{$x/p:patient-id/text()}</f:patient-id>,
                    <f:patient-name>{$x/p:name/text()}</f:patient-name>,
                    <f:dob>{$x/p:dob/text()}</f:dob>,
                    <f:treatments>
                        {
                            for $t in $x/p:Treatment/p:treatment
                            where $t/t:provide-id=$p/prov:provider-id
                            return $t
                        }
                    </f:treatments>
                }</f:patient>
            }
            </f:patients>
        }</f:providerInfo>
    }
    </f:provider-infos>
};

declare function clin:getDrugInfo($kinic as element(c:Clinic))
as element(f:drug-infos){
    <f:drug-infos>{
            for $x in $kinic/p:Patient
                for $m in $x/p:Treatment/p:treatment
                for $n in $m/t:drug-treatment
                return
          <f:drug-info>{
                <f:diagnosis>{$m/t:diagnosis/text()}</f:diagnosis>,
                <f:drug-name>{$n/t:name/text()}</f:drug-name>,
                <f:durg-dosage>{$n/t:dosage/text()}</f:durg-dosage>,
                <f:drug-patient>
                    
                        <f:patient-name>{$x/p:name/text()}</f:patient-name>
                        <f:patient-id>{$x/p:patient-id/text()}</f:patient-id>
                        <f:patient-dob>{$x/p:dob/text()}</f:patient-dob>
                    
                </f:drug-patient>
          }</f:drug-info>
    }
    </f:drug-infos>
};
