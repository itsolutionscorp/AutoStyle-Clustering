def hey(question):
	if question.strip()=='':
		return 'Fine. Be that way!'
	if question.upper()==question and question.lower()!=question:
		return 'Whoa, chill out!'
	if question[-1]=='?':
		return 'Sure.'
	return "Whatever."
