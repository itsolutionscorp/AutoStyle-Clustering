class Bob:

    def __init__(self):
        self._createAnswers()

    def hey(self, message):
        answer = self._assesMessage(message)
        return answer

    def _createAnswers(self):
        self._DEFAULT_ANSWER = 'Whatever.'
        self._QUESTION = 'Sure.'
        self._YELL = 'Woah, chill out!'
        self._NOCONTENT = 'Fine. Be that way!'

    def _assesMessage(self, message):
        if (self._checkForNoContent(message)):
            return self._NOCONTENT
        if (self._checkForYelling(message)):
            return self._YELL
        if (self._checkForQuestion(message)):
            return self._QUESTION
        return self._DEFAULT_ANSWER

    def _checkForNoContent(self, message):
        return ("" == message.strip())

    def _checkForQuestion(self, message):
        return message.endswith('?')

    def _checkForYelling(self, message):
        return message.isupper()
