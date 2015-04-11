class Bob:
    def hey(self, msg):
        msg = msg.strip()

        if msg:
            if msg.isupper() and any(c.isalpha() for c in msg):
                return "Woah, chill out!"

            if msg.endswith("?"):
                return "Sure."

            return "Whatever."
        else:
            return "Fine. Be that way!"
