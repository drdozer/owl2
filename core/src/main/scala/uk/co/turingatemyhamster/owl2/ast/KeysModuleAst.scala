package uk.co.turingatemyhamster
package owl2
package ast

/**
 *
 *
 * @author Matthew Pocock
 */
trait KeysModuleAst extends KeysModule {

  importedModules : owl2.AxiomsModule =>

  override final type HasKey = ast.HasKey
}

case class HasKey(axiomAnnotations: List[Annotation],
                  objectPropertyExpressions: List[ObjectPropertyExpression],
                  dataPropertyExpressions: List[DataPropertyExpression],
                  classExpression: ClassExpression) extends Axiom