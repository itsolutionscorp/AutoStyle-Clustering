# -*- coding: utf-8 -*-
import re

def hey(entrada):
	regex = re.compile(r'[\n\r\t]')	#BORRAMOS ESPACIOS O RETORNOS
	regex.sub(' ', entrada)
	if not entrada or entrada.isspace(): #COMPROBAR SI ENTRADA ES NULA
		return "Fine. Be that way!"
	if any(c.isalpha() for c in entrada): #COMPROBAR SI ENTRADA ES TODO MAYUSCULAS
		if entrada.upper() == entrada:
			return "Whoa, chill out!"
	if any(c.isalpha() or c.isdigit() for c in entrada):	#COMPROBAR SI ENTRADA TERMINA EN ?	
		if entrada[-1:] == "?":
			return "Sure."
	return "Whatever."				#PARA TODO LO DEMAS

