package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait OntologysImpl extends owl2.Ontologys {

  importedPackages : owl2.Iri =>

  override type Ontology = ast.Ontology[IRI, Annotation, Axiom]

  override final val Ontology: OntologyApi = new OntologyApi {
    override def apply(directlyImports: List[IRI],
                       ontologyIRI: Option[IRI],
                       versionIRI: Option[IRI],
                       ontologyAnnotation: List[Annotation],
                       axioms: List[Axiom]) = ast.Ontology(directlyImports, ontologyIRI, versionIRI, ontologyAnnotation, axioms)

    override def unapply(o: Ontology) = ast.Ontology.unapply(o)
  }

  override final type Annotation = ast.Annotation

  override final val Annotation: AnnotationApi = new AnnotationApi {
    override def unapply(annotation: Annotation) = Some(annotation.annotationAnnotations)
  }

  override final type Axiom = ast.Axiom

  override final val Axiom: AxiomApi = new AxiomApi {
    override def unapply(axiom: Axiom) = Some(axiom.axiomAnnotations)
  }
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