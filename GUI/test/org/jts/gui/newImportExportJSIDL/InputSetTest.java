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

public class InputSetTest extends IdenticalTestBase
{
	String testFileBaseName = "InputSetTest";
	int numberTestFiles = 1;
	boolean runInterpretation = false;
	boolean runNonUnique = true;
	boolean runDeclaredResolution = true;
    public static void main( String[] args )
    {
    	InputSetTest t = new InputSetTest();
        t.setup();
        t.importExportTests();
        t.declaredResolutionTests();
        t.runNonUniqueTest();
    }
	org.jts.jsidl.binding.InputSet castJaxbObject( Object object )
	{
		try
		{
			org.jts.jsidl.binding.InputSet output = ( org.jts.jsidl.binding.InputSet )object;
			return output;
		}
		catch( Exception e )
		{
			Assert.fail( e.getMessage() );
			return null;
		}
	}

	com.u2d.generated.InputSet castJmatterObject( Object object )
	{
		try
		{
			com.u2d.generated.InputSet output = ( com.u2d.generated.InputSet )object;
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
		com.u2d.generated.InputSet jmatterObject = org.jts.gui.JAXBtoJmatter.InputSet.lookupOrCreate( castJaxbObject( object ), currentTestName + "1.0" );
		
		return jmatterObject;
	}

	@Override
	Object jmatterToJaxb( Object object )
	{
		// convert back to jaxb object
		org.jts.jsidl.binding.InputSet convertedJaxbObject = org.jts.gui.jmatterToJAXB.InputSet.convert( castJmatterObject( object ) );
		
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
		com.u2d.generated.InputSet jmatterObject = ( com.u2d.generated.InputSet ) object;

		//int length = jmatterObject.getInterpretation().toString().length();

		return -1;
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
		org.jts.jsidl.binding.InputSet input = ( org.jts.jsidl.binding.InputSet )inputObject;

		org.jts.gui.importJSIDL.declaredElementsResolution.InputSet.resolveDeclaredElements( input );

		return input;
	}

}
