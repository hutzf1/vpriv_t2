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
public class __Decommit {
    
    
    public static Element Decommit(Element key) {
    
        PedersenScheme ps = new PedersenScheme();
        
        // Decommit
        BooleanElement result = commitmentScheme.decommit(message, randomization, commitment);
        
        // Create message and randomization to commit
        Element message = ps.commitmentScheme.getMessageSpace().getElement(42);

	// Create commitment
	Element commitment = commitmentScheme.commit(message, key);
                
        return commitment;
    }

}