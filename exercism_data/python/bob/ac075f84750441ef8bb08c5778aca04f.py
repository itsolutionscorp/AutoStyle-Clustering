class Bob:
    def hey(self, text):
        if not text or not text.strip():
            return "Fine. Be that way!"
        elif text == text.upper():
            return "Woah, chill out!"
        elif text[-1] == '?':
            return "Sure."
        else:
            return "Whatever."
