# -*- coding: utf-8 -*-

import unicodedata
 
def normalize(what):
    """ Normalise (normalize) unicode data in Python to remove umlauts, accents etc. """
    if isinstance(what, str):
    	result = what
        return result
    elif isinstance(what, unicode):
		result = unicodedata.normalize('NFKD', what).encode('ASCII', 'ignore')
		return result

def hey(what):
	
	#normalize input to remove accents
	normal= normalize(what)
	final= what.strip()


	if not final:
		return "Fine. Be that way!"
		
	if normal.isupper():
		return "Whoa, chill out!"

	if final.endswith('?'):
		return "Sure."

	else:
		return "Whatever."
