class Bob(object):
    def hey(self, s):
        if s.upper() == s and s.lower() != s:
            # If s is uppercase, and uppercase is actually different
            # from lowercase then this is yelling
            return "Woah, chill out!"
        elif s.endswith("?"):
            return "Sure."
        elif s.strip() == '':
            return "Fine. Be that way!"

        return "Whatever."
