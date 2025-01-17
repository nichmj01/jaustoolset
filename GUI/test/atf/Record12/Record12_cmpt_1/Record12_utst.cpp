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

#include <iostream>

#include "urn_org_jts_test_Record1_1_0/Messages/MessageSet.h"

#include "cppunit/TestCaller.h"
#include "cppunit/TestCaller.h"
#include "cppunit/ui/text/TestRunner.h"
#include "cppunit/TestSuite.h"

using namespace urn_org_jts_test_Record1_1_0;

#define OUT_0  std::cout
#define OUT_1  if (bVerbose<=2) std::cout 
#define OUT_2  if (bVerbose<=1) std::cout

class JTSRecord12MessageTest : public CppUnit::TestFixture {
             
private:
  MsgIn m_MsgIn1,m_MsgIn2;
  int bVerbose;
  
public:
  JTSRecord12MessageTest() {
     bVerbose=0;
  }     
       
  void setUp()
  {
  }

  void tearDown() 
  {
  }

  void testConstructionDefaults()
  {
    OUT_1 << std::endl << "  [executing test (testConstructionDefaults)] " << std::endl;

    OUT_2 << "     --- verifying MsgIn constructs with correct default MessageID" << std::endl;
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 1 );

  }
  
  void testSetGetOperations()
  {
    unsigned t;
    std::string S1,S2,S3;
    int i;
    
    OUT_1 << std::endl << "  [executing test (testSetGetOperations)(TP_3.3.5.1)] " << std::endl;
    
    OUT_2 << "     --- verifying setMessageID/getMessageID reciprocity" << std::endl;
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(5);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 5 );
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(1);
    CPPUNIT_ASSERT( m_MsgIn1.getHeader()->getHeaderRec()->getMessageID() == 1 );

    OUT_2 << "     --- verifying set/getVarLenStr reciprocity" << std::endl;  
     
    S1.clear();
    for (i=0;i<500;i++) 
       S1.append("1234567890");
    for (i=0;i<200;i++)
       S2.append("ABCDEFGHIJKLMNOPQRSTUVWXY");   
    m_MsgIn1.getBody()->getRecord12()->setVariableLengthString1(S1);
    m_MsgIn2.getBody()->getRecord12()->setVariableLengthString1(S2);
    
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getRecord12()->getVariableLengthString1() == S1);
    CPPUNIT_ASSERT(m_MsgIn2.getBody()->getRecord12()->getVariableLengthString1() == S2);

    OUT_2 << "     --- verifying enforcement of maximum length" << std::endl;
    S3 = S1;
    S3.append(1,'e');
    m_MsgIn1.getBody()->getRecord12()->setVariableLengthString1(S3);

    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getRecord12()->getVariableLengthString1() != S3);
    CPPUNIT_ASSERT(m_MsgIn1.getBody()->getRecord12()->getVariableLengthString1() == S1);
        
    OUT_2 << "     --- verifying set/getField reciprocity" << std::endl;    
    
    m_MsgIn1.getBody()->getRecord12()->setField1(25);
    m_MsgIn2.getBody()->getRecord12()->setField1(-15);    
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord12()->getField1() == 25);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getField1() == -15);    
    
    OUT_2 << "     --- verifying set/getStrFld reciprocity" << std::endl;    
    m_MsgIn1.getBody()->getRecord12()->setStrFld("Hello");
    m_MsgIn2.getBody()->getRecord12()->setStrFld("World!");
        
    CPPUNIT_ASSERT( m_MsgIn1.getBody()->getRecord12()->getStrFld() == "Hello");
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getStrFld() == "World!");    

          
    OUT_1 <<  "  [completed test (testSetGetOperations)] " << std::endl;
             
  }

  void testEncodeToSpec()
  {
    unsigned char buff[8192],c;
    std::string S,S2;
    int i;
/*
			jByte m_Field1;
			jFixedLengthString m_StrFld;
			jVariableLengthString m_VariableLengthString1;

*/    
    
    S.clear();
    for (i=85;i<168;i++) {
      c=(unsigned char)i;
      S.append(((const char *)&c));
    }
          
       
    
    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(256);
    m_MsgIn1.getBody()->getRecord12()->setField1(2);
    S2.clear();
    for (i=3;i<83;i++)
      S2.append(1,(unsigned char)i);    
    m_MsgIn1.getBody()->getRecord12()->setStrFld(S2);    
    
    m_MsgIn1.getBody()->getRecord12()->setVariableLengthString1(S);    

    OUT_2 << "     --- verifying message level encode is AS-5684 compliant" << std::endl;
    m_MsgIn1.encode(buff);

    CPPUNIT_ASSERT( m_MsgIn1.getSize() == 168);
    
        
    for (i=0;i<m_MsgIn1.getSize();i++) {
           c=i % 256;
           if (i==84)
              c=0; 
           CPPUNIT_ASSERT( buff[i]==c);
    }   
    OUT_1 << "  [completed test (testEncodeToSpec)] " << std::endl;
       
  }
  
  void testEncodeDecodeOperations()
  {
    int i;
    unsigned char buff[4096];
    
    std::string S1("This string belongs to m_MsgIn1");
    std::string S2("blah!  blah!   blah-blah-blah-BLAAAAH---blah-blah-----blah-blah!"); 
       
    OUT_1 << std::endl << "  [executing test (testEncodeDecodeOperations)(TP_3.3.5.3)] " << std::endl;
    
    OUT_2 << "     --- verifying message level encode/decode reciprocity" << std::endl;

    m_MsgIn1.getHeader()->getHeaderRec()->setMessageID(41);
    m_MsgIn1.getBody()->getRecord12()->setField1(77);
    m_MsgIn1.getBody()->getRecord12()->setStrFld("This is the value of m_MsgIn1 StrFld");   
    m_MsgIn1.getBody()->getRecord12()->setVariableLengthString1(S1);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(127);
    m_MsgIn2.getBody()->getRecord12()->setField1(127);    
    m_MsgIn2.getBody()->getRecord12()->setStrFld("This is ***NOT*** the value of m_MsgIn1 StrFld");    
    m_MsgIn2.getBody()->getRecord12()->setVariableLengthString1(S2);
    
    m_MsgIn1.encode(buff);
    m_MsgIn2.decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getField1() == 77);    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getStrFld() == "This is the value of m_MsgIn1 StrFld");    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getVariableLengthString1() == S1);
               
    OUT_2 << "     --- verifying header level encode/decode reciprocity" << std::endl;
    
    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->encode(buff);
    m_MsgIn2.getHeader()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);

    m_MsgIn2.getHeader()->getHeaderRec()->setMessageID(0);
    m_MsgIn1.getHeader()->getHeaderRec()->encode(buff);
    m_MsgIn2.getHeader()->getHeaderRec()->decode(buff);
    CPPUNIT_ASSERT( m_MsgIn2.getHeader()->getHeaderRec()->getMessageID() == 41);

    OUT_2 << "     --- verifying record level encode/decode reciprocity" << std::endl;

    m_MsgIn2.getBody()->getRecord12()->setField1(127);    
    m_MsgIn2.getBody()->getRecord12()->setStrFld("This is ***NOT*** the value of m_MsgIn1 StrFld");    
    m_MsgIn2.getBody()->getRecord12()->setVariableLengthString1(S2);
        
    m_MsgIn1.getBody()->encode(buff);
    m_MsgIn2.getBody()->decode(buff);
    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getField1() == 77);    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getStrFld() == "This is the value of m_MsgIn1 StrFld");    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getVariableLengthString1() == S1);

    m_MsgIn2.getBody()->getRecord12()->setField1(127);    
    m_MsgIn2.getBody()->getRecord12()->setStrFld("This is ***NOT*** the value of m_MsgIn1 StrFld");    
    m_MsgIn2.getBody()->getRecord12()->setVariableLengthString1(S2);
        
    m_MsgIn1.getBody()->getRecord12()->encode(buff);
    m_MsgIn2.getBody()->getRecord12()->decode(buff);

    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getField1() == 77);    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getStrFld() == "This is the value of m_MsgIn1 StrFld");    
    CPPUNIT_ASSERT( m_MsgIn2.getBody()->getRecord12()->getVariableLengthString1() == S1);
            
    OUT_1 << "  [completed test (testEncodeDecodeOperations)] " << std::endl;
       
  }

  void testEquality()
  {
    OUT_1 << std::endl << "  [executing test (testEquality)] " << std::endl;
    OUT_2 << "     --- verifying message equality operator" << std::endl;    
    CPPUNIT_ASSERT( m_MsgIn1 == m_MsgIn2 );
    OUT_1 << "  [completed test (testEquality)] " << std::endl;
    
  }
  
  void testInequality()
  {
    OUT_1 << std::endl << "  [executing test (testInequality)] " << std::endl;
    OUT_2 << "     --- verifying message inequality operator" << std::endl;    
       
    CPPUNIT_ASSERT( !(m_MsgIn1 != m_MsgIn2) );
    OUT_1 << "  [completed test (testInequality)] " << std::endl;
    
  }

  static CppUnit::Test *suite()
  {
    CppUnit::TestSuite *suiteOfTests = new CppUnit::TestSuite( "JTSRecord12MessageTest" );
    
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord12MessageTest>( 
                                   "testConstructionDefaults", 
                                   &JTSRecord12MessageTest::testConstructionDefaults ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord12MessageTest>( 
                                   "testSetGetOperations (TP_3.3.5.1)", 
                                   &JTSRecord12MessageTest::testSetGetOperations ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord12MessageTest>( 
                                   "testEncodeToSpec (TP_3.3.5.2)", 
                                   &JTSRecord12MessageTest::testEncodeToSpec ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord12MessageTest>( 
                                   "testEncodeDecodeOperations (TP_3.3.5.3)", 
                                   &JTSRecord12MessageTest::testEncodeDecodeOperations ) );                                   
                                                                      
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord12MessageTest>( 
                                   "testEquality", 
                                   &JTSRecord12MessageTest::testEquality ) );
                                   
    suiteOfTests->addTest( new CppUnit::TestCaller<JTSRecord12MessageTest>(
                                   "testInequality",
                                   &JTSRecord12MessageTest::testInequality ) );
    return suiteOfTests;
  }

};


int main( int ac, char **av )
{
  CppUnit::TextUi::TestRunner runner;
  runner.addTest( JTSRecord12MessageTest::suite() );
  
  OUT_0 << std::endl << "------------------------------------------------" << std::endl;
  OUT_0 << "Executing suite JTSRecord12MessageTest" << std::endl;
  runner.run();
  OUT_0 << std::endl << "Completed suite JTSRecord12MessageTest" << std::endl;
  OUT_0 << "------------------------------------------------" << std::endl;  
}

