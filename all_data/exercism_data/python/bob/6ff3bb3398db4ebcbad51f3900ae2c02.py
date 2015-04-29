# -*- coding: utf-8 -*-

def hey(string):
	
    if string.isupper()==True:
	return u'Whoa, chill out!'
    elif len(string.expandtabs().replace(" ",""))==0:
	return u'Fine. Be that way!'
    if string[-1]=="?":
	return u'Sure.'
    else:
	return u'Whatever.'
