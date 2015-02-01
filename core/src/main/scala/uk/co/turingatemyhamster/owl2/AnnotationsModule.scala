package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Annotations
 *
 * @author Matthew Pocock
 */
trait AnnotationsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.OntologyModule with owl2.IriModule =>

  type AnnotationValue

  type AnonymousIndividual <: AnnotationValue

  type IRI <: AnnotationValue

  type Literal <: AnnotationValue

}
