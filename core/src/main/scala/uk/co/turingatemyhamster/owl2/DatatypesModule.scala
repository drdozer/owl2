package uk.co.turingatemyhamster.owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Datatype_Maps
 *
 * @author Matthew Pocock
 */
trait DatatypesModule extends NumbersModule
with FloatingPointNumbersModule
with StringsModule
with BooleanValuesModule
with BinaryDataModule
with IRIsModule
with TimeInstantsModule
with XMLLiteralsModule

trait NumbersModule {
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

trait FloatingPointNumbersModule {
  type `xsd:double`
  type `xsd:float`
}

trait StringsModule {
  type `xsd:string`
  type `xsd:normalizedString`
  type `xsd:token`
  type `xsd:language`
  type `xsd:Name`
  type `xsd:NCName`
  type `xsd:NMTOKEN`
}

trait BooleanValuesModule {
  type `xsd:boolean`
}

trait IRIsModule {
  type `xsd:anyIRI`
}

trait BinaryDataModule {
  type `xsd:hexBinary`
  type `xsd:base64Binary`
}

trait TimeInstantsModule {
  type `xsd:dateTime`
  type `xsd:dateTimeStamp`
}

trait XMLLiteralsModule {
  type `rdf:XMLLiteral`
}