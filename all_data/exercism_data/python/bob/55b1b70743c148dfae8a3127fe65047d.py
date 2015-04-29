class Bob:
    def hey(self, sentence):
        # If silence
        if not sentence.strip():
            return "Fine. Be that way!"
        # If shouting
        elif any(c.isalpha() for c in sentence) and sentence == sentence.upper():
            return "Woah, chill out!"
        # If question
        elif sentence.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
