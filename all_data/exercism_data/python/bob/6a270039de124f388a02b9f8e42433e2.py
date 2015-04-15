class Bob(object):
    """A Bob object to the first test"""
    def hey(self, sentence=""):
        """You say him something and he answer you"""
        if sentence == "" or sentence.replace(" ", "") == "":
            return "Fine. Be that way!"
        if sentence.isupper():
            return "Woah, chill out!"
        if sentence[-1] == "?":
            return "Sure."
        return "Whatever."
