class Bob(object):
    def hey(self, sentence=""):
        """
        Receive a sentence and reply as a lackadaisical teenager

        @type sentence: string
        """
        if not sentence or not sentence.rstrip():
            return "Fine. Be that way."

        if sentence.isupper():
            return "Woah, chill out!"

        if sentence.endswith("?"):
            return "Sure."

        return "Whatever."
