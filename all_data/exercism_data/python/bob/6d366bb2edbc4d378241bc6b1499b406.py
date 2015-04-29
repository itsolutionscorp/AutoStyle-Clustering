# bob module
import string
##
##Bob answers 'Sure.' if you ask him a question.
##
##He answers 'Whoa, chill out!' if you yell at him.
##
##He says 'Fine. Be that way!' if you address him without actually saying
##anything.
##
##He answers 'Whatever.' to anything else.

def hey(phrase = ''):
        if not isinstance(phrase, basestring) or phrase.rstrip() == '':
		return u'Fine. Be that way!'
	elif phrase.isupper() or ('!' in phrase and (phrase.find('!') == len(phrase)-1)):
                return u'Whoa, chill out!'
        elif ('?' in phrase) and (phrase.find('?') == len(phrase)-1):
                return u'Sure.'
	else:
		return u'Whatever.'
                
