class Bob(object):
    def hey(self, tell_bob):
        tell_bob = tell_bob.rstrip()
        if not tell_bob:
            return "Fine. Be that way!"
        if tell_bob.isupper():
            return "Woah, chill out!"
        if tell_bob[-1] == "?":
            return "Sure."
        return "Whatever."
