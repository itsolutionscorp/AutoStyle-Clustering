class Bob(object):
    def hey(self, message):
        message = normalize(message)
        if is_silent(message):
            return "Fine. Be that way!"
        if is_yelled(message):
            return "Woah, chill out!"
        if is_question(message):
            return "Sure."
        return "Whatever."

def normalize(message):
    if message is None:
        return ""
    return message.strip()

def is_silent(message):
    return len(message) == 0

def is_yelled(message):
    return message.isupper()

def is_question(message):
    return message.endswith("?")
