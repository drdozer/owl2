package uk.co.turingatemyhamster
package owl2
package ast


sealed trait IRI extends AnnotationValue

/** A full IRI string, without the wrapping < (U+3C) and > (U+3E) characters. */
case class FullIRI(iriString: String) extends IRI

/** A prefix string, matching the as PNAME_NS production of SPARQL. */
case class PrefixName(prefixString: String)

/** An abbreviated IRI, matching the PNAME_LN production of SPARQL. */
case class AbbreviatedIRI(abbreviatedString: String) extends IRI

trait IriModuleImpl extends owl2.IriModule {

  override final type fullIRI = ast.FullIRI
  override final type prefixName = ast.PrefixName
  override final type abbreviatedIRI = ast.AbbreviatedIRI
  override final type IRI = ast.IRI

}