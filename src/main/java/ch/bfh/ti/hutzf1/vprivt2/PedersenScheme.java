/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import ch.bfh.unicrypt.crypto.schemes.commitment.classes.PedersenCommitmentScheme;
import ch.bfh.unicrypt.math.algebra.general.classes.BooleanElement;
import ch.bfh.unicrypt.math.algebra.general.interfaces.CyclicGroup;
import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;
import ch.bfh.unicrypt.math.algebra.multiplicative.classes.GStarModSafePrime;

/**
 *
 * @author fh
 */

public class PedersenScheme {

    // Create cyclic group G_q (modulo 167)
    private final CyclicGroup CYCLICGROUP = GStarModSafePrime.getInstance(2698727);
    // Create commitment scheme to be used
    private final PedersenCommitmentScheme COMMITMENTSCHEME = PedersenCommitmentScheme.getInstance(CYCLICGROUP);
    
    public Element getTag() {
        Element message = COMMITMENTSCHEME.getRandomizationSpace().getRandomElement();
        return message;
    }
    
    public Element getKey() {
        Element message = COMMITMENTSCHEME.getRandomizationSpace().getRandomElement();
        return message;
    }
    
    public Element getOpeningKey() {
        Element randomization = COMMITMENTSCHEME.getRandomizationSpace().getRandomElement();
        return randomization;
    }
    
    public Element getElement(int value) {
        return COMMITMENTSCHEME.getMessageSpace().getElement(value);
    }
    
    public Element commit(Element message, Element key) {
        Element commitment = COMMITMENTSCHEME.commit(message, key);
        return commitment;
    }
    
    public BooleanElement decommit(Element message, Element key, Element commitment) {
        BooleanElement result = COMMITMENTSCHEME.decommit(message, key, commitment);
        return result;
    }
    
}
