genericResponse = 'Whatever.'
silentTreatmentResponse = 'Fine. Be that way!'
yellingResponse = 'Whoa, chill out!'
questionResponse = 'Sure.'

def hey(Question):
	if Question.strip() == '':
		return silentTreatmentResponse
	if Question.isupper():
		return yellingResponse
	if Question.endswith('?'):
		return questionResponse

	return genericResponse
