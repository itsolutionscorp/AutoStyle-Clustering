import re

def _is_question(message):
    return (not _is_silence(message) and 
            not _is_yelling(message) and
            message[-1] == "?")

def _is_yelling(message):
    return (not _is_silence(message) and
            message.isupper())

def _is_silence(message):
    return (message is None or
            message.strip() == "")

class Bob(object):
    def __init__(self):
        self.__question_reply = "Sure."
        self.__yelling_reply = "Woah, chill out!"
        self.__silence_reply = "Fine. Be that way!"
        self.__other_reply = "Whatever."

        self.__replies = {
            _is_question: self.__question_reply,
            _is_yelling: self.__yelling_reply,
            _is_silence: self.__silence_reply
        }

    def hey(self, message):
        for matcher in self.__replies:
            if matcher(message):
                return self.__replies[matcher]
        return self.__other_reply
