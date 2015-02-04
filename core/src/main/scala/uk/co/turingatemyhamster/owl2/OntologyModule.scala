package uk.co.turingatemyhamster.owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Ontologies
 *
 * @author Matthew Pocock
 */
trait OntologyModule {

  importedModules : IriModule =>

  type Ontology <: AnyRef

  type Annotation <: AnyRef
  
  type Axiom <: AnyRef

}
