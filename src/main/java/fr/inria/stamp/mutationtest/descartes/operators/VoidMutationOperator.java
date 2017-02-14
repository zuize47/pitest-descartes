package fr.inria.stamp.mutationtest.descartes.operators;

import org.pitest.reloc.asm.MethodVisitor;
import org.pitest.reloc.asm.Opcodes;
import org.pitest.reloc.asm.Type;
import org.pitest.reloc.asm.commons.Method;

/**
 * A class whose instances are able to mutate void methods and static initializers
 * (Constructors should call super constructors and so they aren't considered here)
 */
public final class VoidMutationOperator  implements MutationOperator {

    /**
     * Returns a value indicating whether the operator can transform the given method.
     * In this case tells if the given method is void.
     *
     * @param method Method to be tested by the operator
     * @return True if the given method is void, false otherwise
     */
    public boolean canMutate(Method method) {
        //TODO: Detect methods that contain only calls to logging classes or System.out
        return !method.getName().equals("<init>") && method.getReturnType().equals(Type.VOID_TYPE);
    }

    public void generateCode(MethodVisitor mv) {
        mv.visitInsn(Opcodes.RETURN);
    }

    //Singleton pattern implementation
    private static final VoidMutationOperator instance = new VoidMutationOperator();

    public static VoidMutationOperator get() { return instance; }

    private VoidMutationOperator() {}

}