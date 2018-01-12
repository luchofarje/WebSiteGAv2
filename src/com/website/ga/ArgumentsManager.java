package com.website.ga;

import java.util.Hashtable;

import Exceptions.ArgumentException;

public class ArgumentsManager {
	
	private Hashtable<Argument, String> _arguments = new Hashtable<Argument, String>();
	
	public enum Argument{
		viewId,
		config,
		
	}
	
	public ArgumentsManager(String[] arguments){
		parseArgs(arguments);
	}

	private void parseArgs(String[] arguments){
		for(String arg : arguments){
			String[] args = arg.split("=");
			Argument argName = Argument.valueOf(args[0]);
			_arguments.put(argName, args[1]);
		}
	}
	
	public String getArgValue(Argument argument) throws ArgumentException{
		if ( _arguments.containsKey(argument)){
			return _arguments.get(argument);
		}
		throw new IllegalArgumentException(String.format("Missing argument: %s ", argument.toString()));
	}
	
}
