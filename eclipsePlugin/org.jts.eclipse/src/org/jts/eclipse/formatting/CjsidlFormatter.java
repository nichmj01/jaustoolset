/*
 * generated by Xtext
 */
package org.jts.eclipse.formatting;

import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.formatting.impl.AbstractDeclarativeFormatter;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.util.Pair;
import org.jts.eclipse.services.CjsidlGrammarAccess;

/**
 * This class contains custom formatting description.
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#formatting
 * on how and when to use it 
 * 
 * Also see {@link org.eclipse.xtext.xtext.XtextFormattingTokenSerializer} as an example
 */
public class CjsidlFormatter extends AbstractDeclarativeFormatter {
	
	@Override
	protected void configureFormatting(FormattingConfig c) {
	    CjsidlGrammarAccess f = (CjsidlGrammarAccess) getGrammarAccess();
	    
	    GenericFormatter.genericFormatting(c, f);
	    
	    c.setAutoLinewrap(120);
	    
	    // find common keywords an specify formatting for them
	    for (Pair<Keyword, Keyword> pair : f.findKeywordPairs("(", ")")) {
	      c.setNoSpace().after(pair.getFirst());
	      c.setNoSpace().before(pair.getSecond());
	    }
	    for (Keyword comma : f.findKeywords(",")) {
	      c.setNoSpace().before(comma);
	    }	    
	    for(Keyword period: f.findKeywords("."))
	    {
	        c.setNoSpace().around(period);
	    }
	    for (Keyword equal : f.findKeywords("=")) {
	          c.setNoLinewrap().around(equal);
	        }
	    
	    for(Pair<Keyword, Keyword> pair: f.findKeywordPairs("[", "]"))
	    {
	        c.setNoSpace().after(pair.getFirst());
	        c.setNoSpace().before(pair.getSecond());
	    }
	    for(Pair<Keyword, Keyword> pair: f.findKeywordPairs("<", ">"))
        {
            c.setNoSpace().after(pair.getFirst());
            c.setNoSpace().before(pair.getSecond());
        }
	    for(Keyword semi: f.findKeywords(";"))
	    {
	        c.setNoSpace().before(semi);
	        c.setNoLinewrap().before(semi);
	    }
	    for(Keyword colon: f.findKeywords(":"))
        {
            c.setNoSpace().around(colon);
        }	
	    for (Keyword repeated : f.findKeywords("repeated")) {
            c.setNoLinewrap().after(repeated);
          }
	    for (Keyword command : f.findKeywords("command")) {
            c.setNoLinewrap().after(command);
          }
	    for(Keyword offset: f.findKeywords("offset"))
	    {
	        c.setNoLinewrap().before(offset);
	    }
	    for(Keyword using: f.findKeywords("using"))
	    {
	        c.setNoLinewrap().after(using);
	        c.setLinewrap().before(using);
	    }
	    for(Keyword arrow: f.findKeywords("->"))
	    {
	        c.setIndentationIncrement().before(arrow);
	    }
	    for(Keyword next: f.findKeywords("next"))
        {
            c.setIndentationIncrement().before(next);
        }	    
	    for(Keyword internal: f.findKeywords("internal"))
	    {
	        c.setIndentationIncrement().after(internal);
	    }
	    for(Keyword popto: f.findKeywords("popto"))
        {
            c.setIndentationDecrement().after(popto);
        }
	    
	    // Rule specific formatting
	    for(Keyword optional: f.findKeywords("optional"))
	    {
	        c.setNoLinewrap().after(optional);
	        c.setLinewrap().before(optional);
	       
	    }
	    for(RuleCall interpretation: f.findRuleCalls(f.getINTERPRETATIONRule()))
	    {
	        c.setLinewrap().around(interpretation);
	    }   


//	    // formatting for grammar rule Line
//	    c.setLinewrap(2).after(f.getLineAccess().getSemicolonKeyword_1());
//	    c.setNoSpace().before(f.getLineAccess().getSemicolonKeyword_1());
//	    
//	    // formatting for grammar rule TestIndentation
//	    c.setIndentationIncrement().after(
//	        f.getTestIndentationAccess().getLeftCurlyBracketKeyword_1());
//	    c.setIndentationDecrement().before(
//	        f.getTestIndentationAccess().getRightCurlyBracketKeyword_3());
//	    c.setLinewrap().after(
//	        f.getTestIndentationAccess().getLeftCurlyBracketKeyword_1());
//	    c.setLinewrap().after(
//	        f.getTestIndentationAccess().getRightCurlyBracketKeyword_3());
//	    
//	    // formatting for grammar rule Param
//	    c.setNoLinewrap().around(f.getParamAccess().getColonKeyword_1());
//	    c.setNoSpace().around(f.getParamAccess().getColonKeyword_1());
	    
	    // formatting for Comments 
	    c.setLinewrap(1, 1, 2).before(f.getSL_COMMENTRule());
	    c.setLinewrap(1, 1, 2).before(f.getML_COMMENTRule());
	    c.setLinewrap(1, 1, 1).after(f.getML_COMMENTRule());
	}
}
