package uk.co.turingatemyhamster
package owl2

/**
 * An abstraction of: http://www.w3.org/TR/2012/REC-owl2-syntax-20121211/#Annotation_Axioms
 *
 * @author Matthew Pocock
 */
trait AnnotationAxiomModule {

  importedModules :  owl2.IriModule with owl2.AxiomsModule with owl2.EntitiesLiteralsAnonymousIndividualsModule =>

  type SubAnnotationPropertyOf <: AnnotationAxiom

  type AnnotationPropertyDomain <: AnnotationAxiom

  type AnnotationPropertyRange <: AnnotationAxiom

  type AnnotationAssertion <: AnnotationAxiom

  type AnnotationSubject <: AnyRef

  type IRI <: AnnotationSubject

  type AnonymousIndividual <: AnnotationSubject
}
