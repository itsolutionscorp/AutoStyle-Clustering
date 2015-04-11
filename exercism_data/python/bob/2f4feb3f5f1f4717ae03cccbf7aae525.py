def hey(s):
	saidNothing = (s.strip() == '')
	if saidNothing:
		return 'Fine. Be that way!'

	hasAnyLetters = (s.upper() != s.lower())
	isYelling = (hasAnyLetters and s == s.upper())
	if isYelling:
		return 'Whoa, chill out!'

	isQuestion = s.strip().endswith('?')
	if isQuestion:
		return 'Sure.'

	return 'Whatever.'
