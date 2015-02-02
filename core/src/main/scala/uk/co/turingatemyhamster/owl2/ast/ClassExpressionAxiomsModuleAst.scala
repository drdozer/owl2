package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait ClassExpressionAxiomsModuleAst extends owl2.ClassExpressionAxiomsModule {

  importedModules : owl2.AxiomsModule {
    type ClassAxiom = ast.ClassAxiom
  } =>

  override final type SubClassOf = ast.SubClassOf

  override final type EquivalentClasses = ast.EquivalentClasses

  override final type DisjointClasses = ast.DisjointClasses

  override final type DisjointUnion = ast.DisjointUnion

}

case class SubClassOf(axiomAnnotations: List[Annotation] = Nil,
                      subClassExpression: ClassExpression,
                      superClassExpression: ClassExpression) extends ClassAxiom

// constraint: classExpressions 2..*
case class EquivalentClasses(axiomAnnotations: List[Annotation],
                             classExpressions: List[ClassExpression]) extends ClassAxiom

// constraint: classExpressions 2..*
case class DisjointClasses(axiomAnnotations: List[Annotation],
                           classExpressions: List[ClassExpression]) extends ClassAxiom

case class DisjointUnion(axiomAnnotations: List[Annotation],
                         disjointClassExpressions: List[ClassExpression],
                         `class`: Class) extends ClassAxiom