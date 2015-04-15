class Bob:
    def hey(self, message):
        # empty
        if not message or not message.strip():
            return "Fine. Be that way!"
        # yell
        elif message.isupper():
            return "Woah, chill out!"
        # question
        elif message.endswith('?'):
            return "Sure."
        # whatever
        else:
            return "Whatever."
