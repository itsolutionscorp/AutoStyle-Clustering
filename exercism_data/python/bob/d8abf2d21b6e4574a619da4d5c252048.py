_RESPONSE_QUESTION = "Sure."
_RESPONSE_YELLING = "Woah, chill out!"
_RESPONSE_NORMAL = "Whatever."
_RESPONSE_EMPTY = "Fine. Be that way!"


class Bob(object):
    @staticmethod
    def hey(msg):
        msg = msg.strip()
        if msg.isupper():
            return _RESPONSE_YELLING
        if msg.endswith('?'):
            return _RESPONSE_QUESTION
        elif msg:
            return _RESPONSE_NORMAL
        else:
            return _RESPONSE_EMPTY
