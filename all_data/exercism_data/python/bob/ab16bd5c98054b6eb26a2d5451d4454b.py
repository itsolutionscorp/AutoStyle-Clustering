def hey(s):

    r = BobRequest(s)
    return r.response


class BobRequest:

    requestMap = {
        'YELL': 'Whoa, chill out!',
        'QUESTION': 'Sure.',
        'SILENCE': 'Fine. Be that way!',
        'OTHER': 'Whatever.'
    }

    def __init__(self, s):
        self.sentence = s

        if self._isWhiteSpace() or not(len(s)):
            self.requestType = 'SILENCE'
        elif self._isUpperCase():
            self.requestType = 'YELL'
        elif self._endsWithQnMark():
            self.requestType = 'QUESTION'
        else:
            self.requestType = 'OTHER'

        self.response = self.requestMap[self.requestType]


    def _endsWithQnMark(self):
        if len(self.sentence):
            return self.sentence[-1] == '?'
        else:
            return False

    def _isUpperCase(self):
        # return self.sentence.decode('utf8').isupper()
        return self.sentence.isupper()

    def _endsWithExMark(self):
        if len(self.sentence):
            return self.sentence[-1] == '!'
        else:
            return False

    def _isWhiteSpace(self):
        return self.sentence.isspace()
