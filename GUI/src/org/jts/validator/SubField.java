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

package org.jts.validator;

public class SubField 
{
	/**
	 * Validates a JSIDL sub field.
	 * <pre>
		sub_field = 
		   element sub_field {
		      attribute name { identifier },
				# 1. Must follow ANSI C syntax            
		      bit_range, value_set,
		      attribute interpretation { text }?
		   }   
 	 * </pre>
  	 * @param subField JSIDL binding object of the sub field to be checked
	 * @throws ValidatorException
	 */
	public static void validate(org.jts.jsidl.binding.SubField subField) throws ValidatorException
	{
		Validator.validateName(subField.getName());
		
		// A subfield must contain both a bit_range and a value_set.
		if (subField.getValueSet() == null)
		{
			throw new ValidatorException("Subfield must include a value_set");
		}
		if (subField.getValueSet().getValueRangeOrValueEnum().isEmpty())
		{
			throw new ValidatorException("Subfield must include a value_set");
		}
		
		// make sure value set and value enums values can be expressed with the amount of bits described in the bit range
		long from = subField.getBitRange().getFromIndex();
		long to = subField.getBitRange().getToIndex();
		long totalBits = to - from + 1;
		long maxRange = 1 << totalBits;

		org.jts.validator.ValueSet.validateInContext(subField.getValueSet(), maxRange, 0, totalBits + " bits");
	}
	
	public static void contextValidate(org.jts.jsidl.binding.SubField subField, int numberBits) throws ValidatorException
	{
		long to = subField.getBitRange().getToIndex();
		
		if( to >= numberBits )
		{
			throw new ValidatorException("Subfield with to index: " + to + 
											" must be less than primitive type's " + numberBits + 
											" bits");
		}
	}
}
