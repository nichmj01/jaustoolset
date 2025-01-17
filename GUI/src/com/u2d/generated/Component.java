/*
 * Component.java
 *
 * This code has been auto-updated by JTS after it has been auto-generated by umlc
 * Created on Wed Oct 05 10:31:04 EDT 2011
 */

package com.u2d.generated;


import com.u2d.model.*;
import com.u2d.app.PersistenceMechanism;
import com.u2d.type.*;
import com.u2d.type.atom.*;
import com.u2d.type.composite.*;
import com.u2d.list.RelationalList;
import com.u2d.reflection.Fld;
import javax.persistence.Entity;
import com.u2d.reflection.Cmd;


@Entity
public  class Component extends AbstractComplexEObject_JTS{

    
    /** Creates a new instance of Component */
    public Component() {
    }
    public Title title() { 
        StringBuffer text = new StringBuffer();
        text.append(name.title() + " ID: " + componentId.title()+ " [Component]");
        return new Title(text.toString());
    }    
    


    // ******    name   ******
    private final StringEO name = new StringEO();
    public StringEO getName() { return name;}

    // ******    componentId   ******
    private final IntEO componentId = new IntEO();
    @Fld(label="ID")
    public IntEO getComponentId() { return componentId;}

    // ******    interpretation   ******
    private final TextEO interpretation = new TextEO();
    public TextEO getInterpretation() { return interpretation;}

   


 
    // *********  serviceSets ******
    private final RelationalList serviceSets = new RelationalList(ServiceSet.class);
    public static final Class serviceSetsType = ServiceSet.class;
    public static final int serviceSetsRelationType = PersistenceMechanism.MANY_TO_MANY;    
    public static final boolean serviceSetsRelationIsInverse = false;
    public RelationalList getServiceSets() { return serviceSets; } 
 
 
/************** Uncomment the following as needed *****************************/
      public static String[] fieldOrder  = {"name", "componentId", "interpretation", "serviceSets"};
//    public static String[] fieldOrder        = {"fieldname1", "fieldname2"};

      ;
//    public static String[] tabViews          = {"fieldname1", "fieldname2"};

      ;
//    public static String[] flattenIntoParent = {"fieldname1", "fieldname2"};

      ;
//    public static String[] identities = {"fieldname1", "fieldname2"};

//    public static String pluralName() { return "Components"; }
      /* Specify a color to represnt this class */
//    public static Color colorCode = new Color(0x2332);

      /* Use a different Icon for each instance. Change photoFieldName. */
//    private transient PhotoIconAssistant assistant = 
//                        new PhotoIconAssistant(this, photoFieldname);
//    public Icon iconLg() { return assistant.iconLg(); }
//    public Icon iconSm() { return assistant.iconSm(); }


      /* Specify the default search field for this class */
//    public static String defaultSearchPath = "";


      /* Specify a custom view for this class. 
//    public EView getMainView()
//    {
//       return new CustomComponentView(this);
//    }
/**********************/
@Cmd(mnemonic='g')
public Object AutoGenerateCode(com.u2d.element.CommandInfo cmdInfo)
{
      com.u2d.app.Context.getInstance().getViewMechanism().message("Generating Code, Please Wait... ");
      try
      {
	    org.jts.gui.codeGenerator.ComponentGenerator generator = new org.jts.gui.codeGenerator.ComponentGenerator( this );
	    generator.generate();
       }
      catch(org.jts.codegenerator.CodeGeneratorException e)
      {
            com.u2d.app.Context.getInstance().getViewMechanism().message("Code Generation Unsuccessful. "+ e.toString() +"...");
      }
return null;
}
	@Cmd(mnemonic='p')
	public Object generatePromelaCode(com.u2d.element.CommandInfo cmdInfo)
	{
	      org.jts.gui.promelaCodeGenerator.CodeGenStart gen = new org.jts.gui.promelaCodeGenerator.CodeGenStart();	      gen.generateCode( this );
		 return null;
	}
        
}
