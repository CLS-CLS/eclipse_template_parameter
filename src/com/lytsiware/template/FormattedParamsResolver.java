package com.lytsiware.template;

import org.eclipse.jface.text.templates.TemplateContext;
import org.eclipse.jface.text.templates.TemplateVariable;
import org.eclipse.jface.text.templates.TemplateVariableResolver;


public class FormattedParamsResolver extends TemplateVariableResolver{

	private static final String COMMA = ",";

	@Override
	protected String resolve(TemplateContext context) {
		TemplateVariable v = new TemplateVariable("enclosing_method_arguments", "", null);
		context.getContextType().resolve(v, context);
		String[] values = v.getValues();
		if (values == null || values.length == 0){
			return "\"";
		}
		return  resolve(values[0]);
		
	}

	private String resolve(String value){
		String[] args = value.split(COMMA);
		StringBuilder result = new StringBuilder();
		for (String arg: args){
			result.append(" ").append(arg).append("=").append("{}");
			
		}
		result.append("\"");
		for (String arg:args){
			result.append(", ").append(arg);
		}
		return result.toString();

	}
}