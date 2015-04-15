class Bob():
    def hey(self, str):
        if str is None:
            return "Fine. Be that way."
        elif str.strip() == "":
            return "Fine. Be that way."
        elif str.isupper():
            return "Woah, chill out!"
        elif str[-1] == '?':
            return "Sure."
        else:
            return "Whatever."
