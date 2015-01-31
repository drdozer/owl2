package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait OntologyModuleImpl extends owl2.OntologyModule {

  importedModules : owl2.IriModule =>

  override type Ontology = ast.Ontology[IRI, Annotation, Axiom]

  override final type Annotation = ast.Annotation

  override final type Axiom = ast.Axiom

}

// constraint: versionIRI.isDefined ==> ontologyIRI.isDefined
// Could encode as:
//   case class OntologyURI(uriValue: IRI, versionIRI: Option[IRI])
case class Ontology[IRI, Annotation, Axiom](directlyImportsDocuments: List[IRI],
                                            ontologyIRI: Option[IRI],
                                            versionIRI: Option[IRI],
                                            ontologyAnnotations: List[Annotation],
                                            axioms: List[Axiom])

trait Annotation {
  def annotationAnnotations: List[Annotation]
}

trait Axiom {
  def axiomAnnotations: List[Annotation]
}