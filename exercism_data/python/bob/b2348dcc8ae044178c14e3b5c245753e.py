class Bob(object):
    def hey(self, text):
        if not text or not text.strip():
            return "Fine. Be that way!"
        if text.upper() == text:
            return "Woah, chill out!"
        if text.endswith("?"):
            return "Sure."

        return "Whatever."
