class Bob:
    def hey(self, query):
        if not query or not query.strip():
            return "Fine. Be that way!"
        elif query.isupper():
            return "Woah, chill out!"
        elif query.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
