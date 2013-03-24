(: XQuery main module :)

import schema namespace c = "http://www.example.org/shema/clinic" at "Clinic.xsd";

import schema namespace p = "http://www.example.org/schema/clinic/patient" at "Patient.xsd";

import schema namespace t = "http://www.example.org/schema/clinic/treament" at "Treament.xsd";

import schema namespace prov = "http://www.example.org/schema/clinic/provider" at "Provider.xsd";

declare namespace f = "http://www.example.org/schema/clinic/function";

import module namespace clin = "http://www.example.com/ClinicModule" at "ClinicModule.xq";

let $clinic := doc("ClinicData.xml")/c:Clinic

return clin:getTreatmentInfo($clinic)
(:return clin:getProviderInfo($clinic):)
(:return clin:getPatientTreatments($clinic,xs:long(12345678)):)
(:return clin:getPatientDrugs($clinic,xs:long(12345678)):)
(:return clin:getDrugInfo($clinic):)