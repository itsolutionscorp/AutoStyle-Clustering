import re


def hey(greeting):
	g = greeting.encode('utf-8','ignore') 
	
	strippedg =  g.strip(' \t\n\r')
	noNum = re.sub('[^A-Za-z]+', '', g)

	#'Whoa, chill out!' if you yell at him.
	#check for all caps, then check for only whitespace, then check to make sure letters exist
	if (greeting.upper() == greeting and len(strippedg) > 0 and len(noNum) > 0):
		
		return 'Whoa, chill out!'
		

	#Bob answers 'Sure.' if you ask him a question.
	elif (g.endswith('?')):
		return 'Sure.'

	#'Fine. Be that way!' if you address him without actually saying anything
	elif (len(strippedg) == 0):
		return 'Fine. Be that way!'


	#'Whatever.' to anything else.
	else:
		return 'Whatever.'
