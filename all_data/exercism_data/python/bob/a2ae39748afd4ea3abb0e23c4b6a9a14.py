class Bob(object):
    def __init__(self):
        self._reply = "Whatever."

    def hey(self,statement):
        if statement.strip() == "":
            self._reply = "Fine. Be that way!"
        elif statement.isupper():
            self._reply = "Woah, chill out!"
        elif statement.endswith("?"):
            self._reply = "Sure."

        return self._reply
