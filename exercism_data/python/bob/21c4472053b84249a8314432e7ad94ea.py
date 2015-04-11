def _is_question(message):
    return (not _is_silence(message) and 
            not _is_yelling(message) and
            message.endswith("?"))

def _is_yelling(message):
    return (not _is_silence(message) and
            message.isupper())

def _is_silence(message):
    return (message is None or
            message.strip() == "")

class Bob(object):
    __question_reply = "Sure."
    __yelling_reply = "Woah, chill out!"
    __silence_reply = "Fine. Be that way!"
    __other_reply = "Whatever."
    
    __replies = {
        _is_question: __question_reply,
        _is_yelling: __yelling_reply,
        _is_silence: __silence_reply
    }

    def hey(self, message):
        for matcher in Bob.__replies:
            if matcher(message):
                return Bob.__replies[matcher]
        return Bob.__other_reply
