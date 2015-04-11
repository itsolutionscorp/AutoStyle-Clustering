class Bob(object):
    def hey(self, text):
        if not text or not text.strip():
            return "Fine. Be that way."
        elif not filter(str.islower, text):
            return "Woah, chill out!"
        elif text.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
