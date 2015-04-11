class Bob:
    def hey(self, remark):

        if not remark.strip():
            return "Fine. Be that way!"
        elif remark.isupper():
            return "Woah, chill out!"
        elif remark.endswith('?'):
            return "Sure."
        else:
            return "Whatever."
