''' created on 23 Sept 2014
	by @jestuber
'''

def hey(msg):
	response = 'Whatever.' #default response

	if not msg or msg.isspace(): #empty msg
		response = 'Fine. Be that way!'
	elif msg.isupper():
		response = 'Whoa, chill out!'
	elif msg.endswith('?'):
		response = 'Sure.'
		
	return response
