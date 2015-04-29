class Bob:
    def hey(self, speech):
        if speech.strip() == "":
            return "Fine. Be that way!"
        elif speech.isupper():
            return "Woah, chill out!"
        elif speech[-1] == '?':
            return "Sure."
        return "Whatever."
