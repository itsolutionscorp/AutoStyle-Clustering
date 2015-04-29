import string


def hey(conversation):
	"""A method used for conversing with bob. Should return whatever b default, 
	chill out if 'yelling' -- currently determined by caps-- 
	and sure if the text is a question. Also returns fine if there is no input."""
	if type(conversation) != type(str):
		raise ValueError("Conversation must be a valid string, not " + str(type(conversation)))
	conversation = ''.join([i for i in conversation if not i in string.whitespace]) 
	# remove whitespace
	if conversation == "":
		return 'Fine. Be that way!'
	
	
	if conversation.upper() == conversation and not ''.join([i for i in conversation if not i in string.digits and not i in string.punctuation]) == '':
		return 'Whoa, chill out!' 		
		# use pythons default caps function to deal with unicode, ETC. Convert every letter in the conversation to caps, 
		# check if it is the same. Numbers aren't affected, nor should they be.
		# we also check to nake sure there are "letters" in the input (something not whitespace, digits, or punctuation) 
		# otherwise we can't tell if the user is yelling and should assume user is not

	elif conversation[-1] == "?": ## questions end with quetion marks
		return 'Sure.'
	else:
		return 'Whatever.'  #default answer
