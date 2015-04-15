class Bob:
	def __init__(self):
		self._answerDict = self._createAnswerDict()

	def hey(self, message):
		messageAssesment = self._assesMessage(message)
		answer = self._createAnswerForAssesment(messageAssesment)
		return answer

	def _createAnswerForAssesment(self, assesment):
		return self._answerDict[assesment]

	def _createAnswerDict(self):
		dict = {}
		dict['defaultAnswer'] = 'Whatever.'
		dict['question'] = 'Sure.'
		dict['yell'] = 'Woah, chill out!'
		dict['noContent'] = 'Fine. Be that way!'
		return dict

	def _assesMessage(self, message):
		if (self._checkForNoContent(message)):
			return 'noContent'
		if (self._checkForYelling(message)):
			return 'yell'
		if (self._checkForQuestion(message)):
			return 'question'
		return 'defaultAnswer'

	def _checkForNoContent(self, message):
		if (len(message) == len(message.split(' ')) - 1):
			return True
		return False

	def _checkForQuestion(self, message):
		if (message.endswith('?')):
			return True
		return False

	def _checkForYelling(self, message):
		trunc = message[0:len(message) - 1]
		if (trunc.isupper()):
			return True
		return False
