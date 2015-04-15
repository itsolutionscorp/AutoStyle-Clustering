class Bob:
	def hey(self, message):
		# Lets make sure what we're playing with is a string
		if message == None or not isinstance(message, basestring):
			return None

		# Clean up our string. Cast to string and replace empty spaces
		if isinstance(message, unicode):
			message.encode('ascii', 'ignore')

		message = message.replace(' ', '');

		response = ''
		if message:
			# check for punctuation at end
			end_char = message[-1]

			# check for shouting
			cap_count = 0
			total_alpha = 0
			for letter in message:
				if letter.isupper():
					cap_count+=1
				
				if letter.isalpha():
					total_alpha+=1

			# Set response
			if total_alpha != 0 and cap_count/total_alpha == 1:
				response = 'Woah, chill out!'
			elif end_char == '?':
				response = 'Sure.'
			else:
				response = 'Whatever.'

		else:
			response = 'Fine. Be that way!'


		return  response
