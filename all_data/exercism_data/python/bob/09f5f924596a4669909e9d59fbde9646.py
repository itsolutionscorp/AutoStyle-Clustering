class Bob(object):
    def hey(self, phrase):
        if not phrase or all(ch.isspace() for ch in phrase):
            return "Fine. Be that way!"
        elif any(ch.isalpha() for ch in phrase) and all(ch.isupper() for ch in phrase if ch.isalpha()):
            return "Woah, chill out!"
        elif phrase.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
