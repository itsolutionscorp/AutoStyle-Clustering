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
        tmp_phr = phrase.strip()
        response = u'Whatever.'
        if not isinstance(tmp_phr, basestring) or tmp_phr == '':
		response = u'Fine. Be that way!'
	elif tmp_phr.isupper():
                response = u'Whoa, chill out!'
        elif tmp_phr.endswith('?'):
                response = u'Sure.'
	return response
                
