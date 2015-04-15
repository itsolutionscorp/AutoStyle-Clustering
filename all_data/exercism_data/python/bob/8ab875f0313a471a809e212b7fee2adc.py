#!/usr/local/bin/python
# -*- coding: utf8 -*-

import re

class Bob:
	"""Class bob impements bob person"""
	__RESPONSE_TO_QUESTION = "Sure."
	__RESPONSE_TO_ANYTHING_ELSE = "Whatever."
	__RESPONSE_TO_YELL = "Woah, chill out!"
	__RESPONSE_TO_EMPTY = "Fine. Be that way!"

	def hey(self,sMessage):

		#is it empty?
		if self.__isEmpty(sMessage):
			return self.__RESPONSE_TO_EMPTY
		
		#is it yelling.
		if self.__isUpperCase(sMessage):
			return self.__RESPONSE_TO_YELL

		#is it a question? 
		if self.__isQuestion(sMessage):
			return self.__RESPONSE_TO_QUESTION

		#it is other cases		
		return self.__RESPONSE_TO_ANYTHING_ELSE
	
	def __isUpperCase(self,string):
		prog = re.compile("[a-zÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÛÝÞ]",re.IGNORECASE|re.UNICODE)
		result = prog.search(string)
		if result is not None:
			return string.upper() == string
		else:
			return False

	def __isQuestion(self,string):
		return re.search("\\?$",string)

	def __isEmpty(self,string):
		return (string is None) or (string.strip() == "")		
