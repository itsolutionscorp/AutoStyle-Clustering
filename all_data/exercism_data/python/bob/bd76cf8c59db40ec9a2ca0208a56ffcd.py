class Bob:
    def hey(self, str):
        if self.silent(str):
            return "Fine. Be that way."
        elif self.shouting(str):
            return "Woah, chill out!"
        elif self.asking(str):
            return "Sure."
        else:
            return "Whatever."

    def silent(self, str):
        return str is None or str.strip().__len__() == 0

    def shouting(self, str):
        return str == str.upper()

    def asking(self, str):
        return str[-1] == '?'
