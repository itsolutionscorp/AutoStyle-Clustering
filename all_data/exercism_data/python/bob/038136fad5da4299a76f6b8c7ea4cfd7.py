class Bob(object):
    def hey(self, message):
        message = message.strip()
        if not message:
            reply = "Fine. Be that way!"
        elif (message.upper() == message
              and not message.lower() == message):
            reply = "Woah, chill out!"
        elif message[-1] is "?":
            reply = "Sure."
        else:
            reply = "Whatever."
        return reply
