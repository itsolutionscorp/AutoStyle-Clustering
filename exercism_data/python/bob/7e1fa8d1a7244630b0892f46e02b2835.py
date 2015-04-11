whatev = 'Whatever.'
chill  = 'Whoa, chill out!'
sure   = 'Sure.'
fine   = 'Fine. Be that way!'

# String -> String
def hey(msg):

	# check for blank space only

	if msg.strip() == '':
		return fine

	
	# check for yelling

	if msg.upper() == msg and msg.lower() != msg:
		return chill

		# check for question first

	lastChar = msg[-1]
	if lastChar == '?':
		return sure



	# else whatever

	return whatev
