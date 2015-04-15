def hey(req):

    r = BobRequest(req)
    return r.response


class BobRequest:

    requestMap = {
        'YELL': 'Whoa, chill out!',
        'QUESTION': 'Sure.',
        'SILENCE': 'Fine. Be that way!',
        'OTHER': 'Whatever.'
    }

    def __init__(self, req):
        self.req = req

        if self._isWhiteSpace():
            self.requestType = 'SILENCE'
        elif self._isUpperCase():
            self.requestType = 'YELL'
        elif self._endsWithQnMark():
            self.requestType = 'QUESTION'
        else:
            self.requestType = 'OTHER'

        self.response = self.requestMap[self.requestType]


    def _endsWithQnMark(self):
        return self.req.endswith('?')

    def _isUpperCase(self):
        return self.req.isupper()

    def _isWhiteSpace(self):
        return self.req.isspace() or not self.req
