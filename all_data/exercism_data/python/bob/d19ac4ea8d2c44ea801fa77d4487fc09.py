class Bob():

    def yeld(self):
        return "Woah, chill out!"

    def asked(self):
        return "Sure."

    def empty(self):
        return "Fine. Be that way."

    def hey(self, string):
        if string == "":
            return self.empty()
        elif string.upper() == string:
            return self.yeld()
        elif string[-1] == "?":
            return self.asked()
        else:
            return "Whatever."
