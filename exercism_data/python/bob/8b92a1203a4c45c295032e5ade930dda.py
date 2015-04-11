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
        self._DEFAULT_ANSWER = 0
        self._QUESTION = 1
        self._YELL = 2
        self._NOCONTENT = 3

        dict = {}
        dict[self._DEFAULT_ANSWER] = 'Whatever.'
        dict[self._QUESTION] = 'Sure.'
        dict[self._YELL] = 'Woah, chill out!'
        dict[self._NOCONTENT] = 'Fine. Be that way!'
        return dict

    def _assesMessage(self, message):
        if (self._checkForNoContent(message)):
            return self._NOCONTENT
        if (self._checkForYelling(message)):
            return self._YELL
        if (self._checkForQuestion(message)):
            return self._QUESTION
        return self._DEFAULT_ANSWER

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
