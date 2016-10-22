/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.bfh.ti.hutzf1.vprivt2;

import ch.bfh.unicrypt.crypto.schemes.commitment.classes.PedersenCommitmentScheme;
import ch.bfh.unicrypt.crypto.schemes.hashing.classes.FixedByteArrayHashingScheme;
import ch.bfh.unicrypt.helper.math.Alphabet;
import ch.bfh.unicrypt.math.algebra.concatenative.classes.StringMonoid;
import ch.bfh.unicrypt.math.algebra.dualistic.classes.Z;
import ch.bfh.unicrypt.math.algebra.general.classes.ProductSet;
import ch.bfh.unicrypt.math.algebra.general.classes.Tuple;
import ch.bfh.unicrypt.math.algebra.general.interfaces.CyclicGroup;
import ch.bfh.unicrypt.math.algebra.general.interfaces.Element;
import ch.bfh.unicrypt.math.algebra.multiplicative.classes.GStarModSafePrime;
import java.math.BigInteger;

/**
 *
 * @author fh
 */
public class Hash {
    
    /*// Create cyclic group G_q (modulo 167)
    private final CyclicGroup CYCLICGROUP = GStarModSafePrime.getInstance(2903);
    // Create commitment scheme to be used
    private final PedersenCommitmentScheme COMMITMENTSCHEME = PedersenCommitmentScheme.getInstance(CYCLICGROUP);
    */
    
    public Element getHash(Element message, Element key) {

            /*
            StringMonoid stringSet = StringMonoid.getInstance(Alphabet.LOWER_CASE);
            //Element<String> stringElement = stringSet.getElement(message);

            Z bigIntegerSet = Z.getInstance();
            //Element<BigInteger> bigIntegerElement = bigIntegerSet.getElement(5);

            ProductSet messageSpace = ProductSet.getInstance(stringSet, bigIntegerSet);
            Tuple tuple = messageSpace.getElement(message, key);
            //            Tuple message = messageSpace.getElement(stringElement, bigIntegerElement);

            FixedByteArrayHashingScheme scheme = FixedByteArrayHashingScheme.getInstance(messageSpace);

            Element hash = scheme.hash(tuple);
            //Element result = scheme.check(message, hash);            

            return hash;
            */  
            
            //Element commitment = COMMITMENTSCHEME.commit(message, key);
            //return commitment;
            
            return message;

    }
    
}
