def is_silence(msg):
    # Only whitespace. Could argue that "..." is also silence...
    return not msg.strip()

def is_question(msg):
    return msg and msg[-1] == '?'

def is_shout(msg):
    return msg.isupper()


class Bob(object):

    def hey(self, msg):
        if is_silence(msg):
            return "Fine. Be that way!"
        elif is_shout(msg):
            return "Woah, chill out!"
        elif is_question(msg):
            return "Sure."
        else:
            return "Whatever."
