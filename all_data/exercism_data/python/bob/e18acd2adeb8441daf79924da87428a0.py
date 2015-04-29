class Bob():
    @staticmethod
    def hey(sentence):
        if sentence == "":
            return "Fine. Be that way."
        if sentence.endswith("?"):
            return "Sure."
        if sentence.isupper():
            return "Woah, chill out!"

        return "Whatever."
