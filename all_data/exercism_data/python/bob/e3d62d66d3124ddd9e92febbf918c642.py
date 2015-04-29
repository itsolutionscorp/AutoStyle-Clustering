class Bob(object):
    def hey(self, message):
        message = message.strip()
        if not message:
            reply = "Fine. Be that way!"
        elif message.isupper():
            reply = "Woah, chill out!"
        elif message.endswith('?'):
            reply = "Sure."
        else:
            reply = "Whatever."
        return reply
