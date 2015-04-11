class Bob(object):

    def hey(self, msg):
        # msg.strip() checks that there contains a message.
        # any(x.isalpha() for x in msg) checks for the existance of letters.
        if msg == msg.upper() and msg.strip():
            if any(x.isalpha() for x in msg):
                return "Woah, chill out!"

        if msg.endswith('?'):
            return "Sure."

        if not msg.strip():
            return "Fine. Be that way!"

        # If nothing else, fall through to a response of 'Whatever.'
        return "Whatever."
