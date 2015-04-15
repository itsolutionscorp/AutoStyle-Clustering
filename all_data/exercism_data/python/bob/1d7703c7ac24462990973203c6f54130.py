class Bob(object):
    def hey(self, str):
        str = (str or '').decode('latin-1')
        response = u'Whatever.'
        if _is_asking_politely(str):
            response = u'Sure.'
        elif _is_shouting(str):
            response = u'Woah, chill out!'
        elif _is_silent(str):
            response = u'Fine. Be that way!'
        return response

def _is_asking_politely(str):
    return not _is_shouting(str) and str[-1:] == u'?'

def _is_shouting(str):
    return str == str.upper() and any(c.isalpha() for c in str)

def _is_silent(str):
    return str.strip() == u''
