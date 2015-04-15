def hey(text):
	#we don't want whitespace!
	text = text.strip()
	#empty?
	if len(text) == 0:
		return 'Fine. Be that way!'
	#check for possible case change
	elif (text.upper() == text and len([x for x in text if x.lower() != x.upper() ]) > 0):
		return 'Whoa, chill out!'
	#non yelling question
	elif text[-1] == '?':
 		return u'Sure.'
	#anything else
	else:
		return u'Whatever.'
