class Bob():

    def hey(self, message):
        if message == "":
            return "Fine. Be that way."
        elif message.isupper():
            return "Woah, chill out!"
        elif message[-1] is "?":
            return "Sure."
        else:
            return "Whatever."
