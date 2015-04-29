class Bob:
    def hey(self, str):
        if str is None or self.__silent(str):
            return "Fine. Be that way."
        elif self.__shouting(str):
            return "Woah, chill out!"
        elif self.__asking(str):
            return "Sure."
        else:
            return "Whatever."

    def __silent(self, str):
        return not str.strip()

    def __shouting(self, str):
        return str == str.upper()

    def __asking(self, str):
        return str.endswith('?')
