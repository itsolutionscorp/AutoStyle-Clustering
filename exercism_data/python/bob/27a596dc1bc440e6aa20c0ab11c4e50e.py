# An attempt without regexes
class Bob(object):
    def __init__(self):
        self._reply = "Whatever."

    def hey(self,statement):
        "A non regex based reply"
        if statement.strip() == "":
            self._reply = "Fine. Be that way!"
        elif statement.upper() == statement.lower(): #NUMBERS, much hacky
            if statement.endswith("?"):
                self._reply = "Sure."
        elif statement.upper() == statement: #YELLING
            self._reply = "Woah, chill out!"
        elif statement.endswith("?"):
            self._reply = "Sure."

        return self._reply
