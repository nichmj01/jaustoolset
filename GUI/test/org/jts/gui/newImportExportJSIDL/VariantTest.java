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

package org.jts.gui.newImportExportJSIDL;

import org.junit.*;

import java.io.File;

public class VariantTest extends IdenticalTestBase
{
	String testFileBaseName = "VariantTest";
	int numberTestFiles = 4;
	boolean runInterpretation = true;
	boolean runNonUnique = true;
	boolean runDeclaredResolution = true;

	org.jts.jsidl.binding.Variant castJaxbObject( Object object )
	{
		try
		{
			org.jts.jsidl.binding.Variant output = ( org.jts.jsidl.binding.Variant )object;
			return output;
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
			return null;
		}
	}

	com.u2d.generated.Variant castJmatterObject( Object object )
	{
		try
		{
			com.u2d.generated.Variant output = ( com.u2d.generated.Variant )object;
			return output;
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
			return null;
		}
	}

	@Override
	void marshal( Object output, File outputFile )
	{
		javax.xml.bind.JAXB.marshal( castJaxbObject( output ), outputFile );
	}

	@Override
	Object jaxbToJmatter( Object object )
	{
		// convert from jaxb to jmatter
		com.u2d.generated.Variant jmatterObject = org.jts.gui.JAXBtoJmatter.Variant.lookupOrCreate( castJaxbObject( object ) );
		
		return jmatterObject;
	}

	@Override
	Object jmatterToJaxb( Object object )
	{
		// convert back to jaxb object
		org.jts.jsidl.binding.Variant convertedJaxbObject = org.jts.gui.jmatterToJAXB.Variant.convert( castJmatterObject( object ) );
		
		return convertedJaxbObject;
	}

	@Override
	String getTestFileBaseName()
	{
		return testFileBaseName;
	}
	
	@Override
	int getNumberTestFiles()
	{
		return numberTestFiles;
	}

	@Override
	int getInterpretationLength( Object object )
	{
		com.u2d.generated.Variant jmatterObject = ( com.u2d.generated.Variant ) object;

		int length = jmatterObject.getInterpretation().toString().length();

		return length;
	}

	@Override
	boolean getRunInterpretation()
	{
		return runInterpretation;
	}

	@Override
	boolean getRunNonUnique()
	{
		return runNonUnique;
	}

	@Override
	boolean getRunDeclaredResolution()
	{
		return runDeclaredResolution;
	}

	@Override
	Object runResolveDeclaredElements( Object inputObject )
	{
		org.jts.jsidl.binding.Variant input = ( org.jts.jsidl.binding.Variant )inputObject;

		org.jts.gui.importJSIDL.declaredElementsResolution.Variant.resolveDeclaredElements( input );

		return input;
	}

}
