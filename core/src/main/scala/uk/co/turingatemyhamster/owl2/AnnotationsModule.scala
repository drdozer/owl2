package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Annotations
 *
 * @author Matthew Pocock
 */
trait AnnotationsModule {

  importedModules : owl2.IriModule with owl2.EntitiesLiteralsAnonymousIndividualsModule =>

  type AnnotationValue

  type AnonymousIndividual <: AnnotationValue

  type IRI <: AnnotationValue

  type Literal <: AnnotationValue

}
