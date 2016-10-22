/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2.crypto;

import ch.bfh.unicrypt.crypto.schemes.commitment.classes.PedersenCommitmentScheme;
import ch.bfh.unicrypt.math.algebra.dualistic.classes.ZModPrime;
import ch.bfh.unicrypt.math.algebra.general.classes.BooleanElement;
import ch.bfh.unicrypt.math.algebra.general.classes.ProductGroup;
import ch.bfh.unicrypt.math.algebra.general.classes.ProductSet;
import ch.bfh.unicrypt.math.algebra.general.classes.Tuple;
import ch.bfh.unicrypt.math.algebra.general.interfaces.CyclicGroup;
import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;
import ch.bfh.unicrypt.math.algebra.general.interfaces.Group;
import ch.bfh.unicrypt.math.algebra.multiplicative.classes.GStarModSafePrime;
import ch.bfh.unicrypt.math.function.classes.HashFunction;
import java.util.function.Function;

/**
 *
 * @author fh
 */
public class CryptTest {

    // Create cyclic group G_q (modulo 2903)
    private static final CyclicGroup CYCLICGROUP = GStarModSafePrime.getInstance(2903);
    // Create commitment scheme to be used
    private static final PedersenCommitmentScheme COMMITMENTSCHEME = PedersenCommitmentScheme.getInstance(CYCLICGROUP);

    public static void main(String[] args) {
        Element e = CryptTest.getElement(284);
        System.out.println(e.toString());

        HashFunction hash = HashFunction.getInstance(CYCLICGROUP);
        hash.apply(e);
        

    }
    
    public static Element getTag() {
        Element message = COMMITMENTSCHEME.getRandomizationSpace().getRandomElement();
        return message;
    }
    
    public static Element getKey() {
        Element message = COMMITMENTSCHEME.getRandomizationSpace().getRandomElement();
        return message;
    }
    
    public static Element getOpeningKey() {
        Element randomization = COMMITMENTSCHEME.getRandomizationSpace().getRandomElement();
        return randomization;
    }
    
    public static Element getElement(int value) {
        return COMMITMENTSCHEME.getMessageSpace().getElement(value);
    }
    
    public static Element commit(Element message, Element key) {
        Element commitment = COMMITMENTSCHEME.commit(message, key);
        return commitment;
    }
    
    public static BooleanElement decommit(Element message, Element key, Element commitment) {
        BooleanElement result = COMMITMENTSCHEME.decommit(message, key, commitment);
        return result;
    }
    
}
