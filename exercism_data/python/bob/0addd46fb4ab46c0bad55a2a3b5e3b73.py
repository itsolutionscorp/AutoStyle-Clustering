class Bob(object):

    def hey(self, message):
        if not message or not message.strip():
            return "Fine. Be that way!"
        elif message == message.upper():
            return "Woah, chill out!"
        elif message.endswith('?'):
            return "Sure."

        return "Whatever."
