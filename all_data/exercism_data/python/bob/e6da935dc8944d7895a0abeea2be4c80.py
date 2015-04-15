class Bob(object):

    def hey(self, msg):
        msg = msg.strip()

        if not msg:
            return "Fine. Be that way!"

        # any(x.isalpha() for x in msg) checks for the existance of letters.
        if msg == msg.upper() and any(x.isalpha() for x in msg):
            return "Woah, chill out!"

        if msg.endswith('?'):
            return "Sure."

        # If nothing else, fall through to a response of 'Whatever.'
        return "Whatever."
