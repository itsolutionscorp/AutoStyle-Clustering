def hey(msg):
	"""
	Bob is a lackadaisical teenager. In conversation, his responses are very
	limited.
	Bob answers 'Sure.' if you ask him a question.
	He answers 'Whoa, chill out!' if you yell at him.
	He says 'Fine. Be that way!' if you address him without actually saying
	anything.
	He answers 'Whatever.' to anything else.
	"""
	msg = msg.strip()
	if not msg:
		return 'Fine. Be that way!'
	if msg.isupper():
		return 'Whoa, chill out!'
	if msg.endswith('?'):
		return 'Sure.' 
	return 'Whatever.'
