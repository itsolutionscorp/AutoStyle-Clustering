class Bob:

    def hey(self, communique):
        if self.__is_silent(communique):
            return "Fine. Be that way!"
        elif self.__is_yelling(communique):
            return "Woah, chill out!"
        elif self.__is_asking(communique):
            return "Sure."
        else:
            return "Whatever."

    def __is_silent(self, communique):
        return communique == None or len(communique.strip()) == 0

    def __is_yelling(self, communique):
        return communique.upper() == communique

    def __is_asking(self, communique):
        return communique[-1] == "?"
