#
# Skeleton file for the Python "Bob" exercise.
#
def hey(what):
	response = BobResponse()
	response_return = ''
	what = what.strip()
	if not what:
		response_return = response.toSilence
	elif what.isupper():
		response_return = response.toYell
	elif what.endswith('?'):
		response_return = response.toQuestion
	else:
		response_return = response.toDefault
	return response_return
    
class BobResponse(object):
	"""BobResponse class helps keep track of all of Bob's responses."""
	def __init__(self):
		super(BobResponse, self).__init__()
		self.toQuestion = 'Sure.'
		self.toYell = 'Whoa, chill out!'
		self.toSilence = 'Fine. Be that way!'
		self.toDefault = 'Whatever.'
