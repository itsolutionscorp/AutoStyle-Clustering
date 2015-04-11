class Bob(object):
    def __init__(self, expected_encoding='latin-1'):
        self._expected_encoding = expected_encoding

    def hey(self, str):
        str = self._normalise(str)
        response = u'Whatever.'
        if _is_asking_politely(str):
            response = u'Sure.'
        elif _is_shouting(str):
            response = u'Woah, chill out!'
        elif _is_silent(str):
            response = u'Fine. Be that way!'
        return response

    def _normalise(self, str):
        return (str or '').decode(self._expected_encoding).strip()

def _is_asking_politely(str):
    return not _is_shouting(str) and str.endswith(u'?')

def _is_shouting(str):
    return str.isupper()

def _is_silent(str):
    return str == u''
