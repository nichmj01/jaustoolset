/***********           LICENSE HEADER   *******************************
JAUS Tool Set
Copyright (c)  2010, United States Government
All rights reserved.

Redistribution and use in source and binary forms, with or without 
modification, are permitted provided that the following conditions are met:

       Redistributions of source code must retain the above copyright notice, 
this list of conditions and the following disclaimer.

       Redistributions in binary form must reproduce the above copyright 
notice, this list of conditions and the following disclaimer in the 
documentation and/or other materials provided with the distribution.

       Neither the name of the United States Government nor the names of 
its contributors may be used to endorse or promote products derived from 
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE 
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.
*********************  END OF LICENSE ***********************************/

package org.jts.gui.jmatterToJAXB;

import java.util.Collections;
import java.util.List;

import org.jts.gui.util.Conversion;
import org.jts.gui.util.SubFieldComparator;

/* Converts JMatter BitField to a JAXB BitField.
*/
public class BitField {

  public static org.jts.jsidl.binding.BitField convert( com.u2d.generated.BitField bitField ) {
    if( bitField == null )
      return null;
      
    org.jts.jsidl.binding.BitField bf = 
                new org.jts.jsidl.binding.BitField();
    
    //attributes
    bf.setName( bitField.getName().toString() );
    bf.setOptional( bitField.getOptional().booleanValue() );
    bf.setFieldTypeUnsigned( bitField.getType().getCode().toString() );
    
    // interpretation
    Conversion.setNonEmptyDescription(bf, bitField);
    
    // sub_fields
    List jmList = bitField.getSubFields().getItems();
    List jxList = bf.getSubField();
    
     for( int ii=0; ii<jmList.size(); ii++)
     {
    	 jxList.add(org.jts.gui.jmatterToJAXB.SubField.convert((com.u2d.generated.SubField)jmList.get(ii)));
     }
     Collections.sort(jxList, new SubFieldComparator());
      
     return bf;
  }
}
