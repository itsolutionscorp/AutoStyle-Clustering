class Bob:
    def hey(self, query):
        if not query or not query.strip():
            return "Fine. Be that way!"
        elif query == query.upper() and any(c.isalpha() for c in query):
            return "Woah, chill out!"
        elif query.endswith("?"):
            return "Sure."
        else:
            return "Whatever."
