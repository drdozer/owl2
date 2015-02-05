package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait AnnotationsModuleAst extends owl2.AnnotationsModule {

  importedModules : owl2.EntitiesLiteralsAnonymousIndividualsModule with owl2.OntologyModule with owl2.IriModule =>

  override final type Annotation = ast.Annotation

}

trait AnnotationValue

case class  Annotation(annotationAnnotations: List[Annotation] = Nil,
                       annotationProperty: AnnotationProperty,
                       annotationValue: AnnotationValue)
