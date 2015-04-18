package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait AnnotationAxiomModuleAst extends owl2.AnnotationAxiomModule {

  importedModules : owl2.AxiomsModule with owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.IriModule {
    type AnnotationAxiom = ast.AnnotationAxiom
  } =>

  override final type SubAnnotationPropertyOf = ast.SubAnnotationPropertyOf

  override final type AnnotationPropertyDomain = ast.AnnotationPropertyDomain

  override final type AnnotationPropertyRange = ast.AnnotationPropertyRange

  override final type AnnotationAssertion = ast.AnnotationAssertion

  override final type AnnotationSubject = ast.AnnotationSubject
}

case class SubAnnotationPropertyOf(axiomAnnotations: List[Annotation] = Nil,
                                   subAnnotationProperty: AnnotationProperty,
                                   superAnnotationProperty: AnnotationProperty) extends AnnotationAxiom

case class AnnotationPropertyDomain(axiomAnnotations: List[Annotation] = Nil,
                                   annotationProperty: AnnotationProperty,
                                   domain: IRI) extends AnnotationAxiom

case class AnnotationPropertyRange(axiomAnnotations: List[Annotation] = Nil,
                                   annotationProperty: AnnotationProperty,
                                   range: IRI) extends AnnotationAxiom

case class asAnnotationAssertion(axiomAnnotations: List[Annotation] = Nil,
                               annotationSubject: AnnotationSubject,
                               annotationProperty: AnnotationProperty,
                               annotationValue: AnnotationValue) extends AnnotationAxiom

trait AnnotationSubject