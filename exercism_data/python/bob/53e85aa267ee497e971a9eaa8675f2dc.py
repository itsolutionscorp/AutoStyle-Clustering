import re
class Bob():
    def hey(self, input):
    	if input.strip()=='':
    		reply='Fine. Be that way!'
        elif input==input.upper() and re.search('[a-zA-Z]',input)!=None:
        	reply='Woah, chill out!'
    	elif input[-1]=='?':
    		reply='Sure.'
    	else:
	    	reply = 'Whatever.'
        return reply
