package uk.co.turingatemyhamster.owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Ontologies
 *
 * @author Matthew Pocock
 */
trait OntologyModule {

  importedModules : IriModule =>

  type Ontology

  def Ontology : OntologyApi

  trait OntologyApi {
    def apply(directlyImportsDocuments: List[IRI],
              ontologyIRI: Option[IRI],
              versionIRI: Option[IRI],
              ontologyAnnotation: List[Annotation],
              axioms: List[Axiom]): Ontology
    def unapply(o: Ontology): Option[(List[IRI], Option[IRI], Option[IRI], List[Annotation], List[Axiom])]
  }

  type Annotation
  
  def Annotation : AnnotationApi
  
  trait AnnotationApi {
    def unapply(annotation: Annotation): Some[List[Annotation]]
  }

  type Axiom
  
  def Axiom : AxiomApi
  
  trait AxiomApi {
    def unapply(axiom: Axiom): Some[List[Annotation]]
  }
}
