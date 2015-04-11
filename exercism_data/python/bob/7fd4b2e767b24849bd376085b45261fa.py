class Bob(object):
    def hey(self, something):
        if not something:
            return "Fine. Be that way."
        if something.endswith("?"):
            return "Sure."
        if something.upper() == something:
            return "Woah, chill out!"  
        return "Whatever."
