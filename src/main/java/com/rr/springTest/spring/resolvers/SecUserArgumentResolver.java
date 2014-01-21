package com.rr.springTest.spring.resolvers;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.rr.springTest.model.SecUserInfo;

/**
 * Argument resolver to allow REST controller methods to ask
 * for a SecUserInfo object as an argument and receive the authenticated
 * user.
 * 
 * @author Ross Romano
 */
//public class SecUserArgumentResolver implements WebArgumentResolver {
public class SecUserArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) 
	{
		if(parameter.getParameterType().equals(SecUserInfo.class))
		{
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception 
	{
		return (SecUserInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}

	/*
	@Override
	public Object resolveArgument(MethodParameter methodParameter,
								  NativeWebRequest webRequest) throws Exception 
	{
		if(methodParameter.getParameterType().equals(SecUserInfo.class))
		{
			Principal principal = webRequest.getUserPrincipal();
			return (SecUserInfo)((Authentication) principal).getPrincipal();
		}
		return WebArgumentResolver.UNRESOLVED;
	}
	*/

}
