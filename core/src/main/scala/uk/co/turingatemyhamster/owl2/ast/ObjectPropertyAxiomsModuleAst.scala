package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait ObjectPropertyAxiomsModuleAst extends owl2.ObjectPropertyAxiomsModule {

  importedModules : owl2.AxiomsModule {
    type ObjectPropertyAxiom = ast.ObjectPropertyAxiom
  } =>

  override final type EquivalentObjectProperties = ast.EquivalentObjectProperties

  override final type DisjointObjectProperties = ast.DisjointObjectProperties

  override final type SubObjectPropertyOf = ast.SubObjectPropertyOf

  override final type ObjectPropertyDomain = ast.ObjectPropertyDomain

  override final type ObjectPropertyRange = ast.ObjectPropertyRange

  override final type InverseObjectProperties = ast.InverseObjectProperties

}

// constraint: objectPropertyExpressions 2..*
case class EquivalentObjectProperties(axiomAnnotations: List[Annotation],
                                      objectPropertyExpressions: List[ObjectPropertyExpression]) extends ObjectPropertyAxiom

// constraint: objectPropertyExpressions 2..*
case class DisjointObjectProperties(axiomAnnotations: List[Annotation],
                                    objectPropertyExpressions: List[ObjectPropertyExpression]) extends ObjectPropertyAxiom

case class SubObjectPropertyOf(axiomAnnotations: List[Annotation],
                               superObjectPropertyExpresssion: ObjectPropertyExpression,
                               subObjectPropertyExpressions: List[ObjectPropertyExpression]) extends ObjectPropertyAxiom

case class ObjectPropertyDomain(axiomAnnotations: List[Annotation],
                                objectPropertyExpression: ObjectPropertyExpression,
                                domain: ClassExpression) extends ObjectPropertyAxiom

case class ObjectPropertyRange(axiomAnnotations: List[Annotation],
                               objectPropertyExpression: ObjectPropertyExpression,
                               range: ClassExpression) extends ObjectPropertyAxiom

case class InverseObjectProperties(axiomAnnotations: List[Annotation],
                                   objectPropertyExpression1: ObjectPropertyExpression,
                                   objectPropertyExpression2: ObjectPropertyExpression) extends ObjectPropertyAxiom