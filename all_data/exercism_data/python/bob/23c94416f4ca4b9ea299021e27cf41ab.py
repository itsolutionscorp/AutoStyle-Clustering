class Bob:
    def hey(self, str):
        if self.silent(str):   return "Fine. Be that way."
        if self.shouting(str): return "Woah, chill out!"
        if self.asking(str):   return "Sure."
        return "Whatever."

    def silent(self, str):
        return str is None or not str.strip()

    def shouting(self, str):
        return str.isupper()

    def asking(self, str):
        return str.endswith('?')
