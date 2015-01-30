package uk.co.turingatemyhamster.owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Datatype_Maps
 *
 * @author Matthew Pocock
 */
trait Datatypes extends Numbers
with FloatingPointNumbers
with Strings
with BooleanValues
with BinaryData
with IRIs
with TimeInstants
with XMLLiterals

trait Numbers {
  type `owl:real`
  type `owl:rational`
  type `xsd:decimal`
  type `xsd:integer`
  type `xsd:nonNegativeInteger`
  type `xsd:nonPositiveInteger`
  type `xsd:positiveInteger`
  type `xsd:negativeInteger`
  type `xsd:long`
  type `xsd:int`
  type `xsd:short`
  type `xsd:byte`
  type `xsd:unsignedLong`
  type `xsd:unsignedInt`
  type `xsd:unsignedShort`
  type `xsd:unsignedbyte`
}

trait FloatingPointNumbers {
  type `xsd:double`
  type `xsd:float`
}

trait Strings {
  type `xsd:string`
  type `xsd:normalizedString`
  type `xsd:token`
  type `xsd:language`
  type `xsd:Name`
  type `xsd:NCName`
  type `xsd:NMTOKEN`
}

trait BooleanValues {
  type `xsd:boolean`
}

trait IRIs {
  type `xsd:anyIRI`
}

trait BinaryData {
  type `xsd:hexBinary`
  type `xsd:base64Binary`
}

trait TimeInstants {
  type `xsd:dateTime`
  type `xsd:dateTimeStamp`
}

trait XMLLiterals {
  type `rdf:XMLLiteral`
}