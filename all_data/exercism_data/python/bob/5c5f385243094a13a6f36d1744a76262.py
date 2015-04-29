import string

_ACRONYMS = ["OK", "DMV"]
def is_yelled(word):
    word = word.strip(string.punctuation + string.digits)
    if len(word) == 0:
        return False
    if word in _ACRONYMS:
        return False
    return word == word.upper()

def safe_strip(message):
    if message is None:
        return ""
    return message.strip()

class Bob(object):
    def hey(self, message):
        message = safe_strip(message)
        if len(message) == 0:
            return "Fine. Be that way!"
        if any(is_yelled(word) for word in message.split()):
            return "Woah, chill out!"
        if message[-1] == "?":
            return "Sure."
        return "Whatever."
