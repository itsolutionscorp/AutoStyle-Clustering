import re

def hey(value):
	pattern = ".*[A-Z]{2}.*"
	other_pattern = ".*[a-z].*"
	final_pattern = u'.*\xe4.*'
	empty_pattern = "[ \t]+$"
        if (re.match(pattern,value) and not re.match(other_pattern,value) and not re.match(final_pattern,value)):
                return 'Woah, chill out!'
	elif (value.endswith('?')):
		return 'Sure.'
	elif ((len(value)==0) or (re.match(empty_pattern,value))):
		return 'Fine. Be that way!'
	else:
		return 'Whatever.'


#Bob is a lackadaisical teenager. In conversation, his responses are very limited.

#Bob answers 'Sure.' if you ask him a question.

#He answers 'Woah, chill out!' if you yell at him.

#He says 'Fine. Be that way!' if you address him without actually saying
#anything.

#He answers 'Whatever.' to anything else.
