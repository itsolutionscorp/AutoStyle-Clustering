class bob(object):
    def hey(message):
        if message.strip() == "":          # Empty string
            return "Fine. Be that way!"
        elif message.isupper():            # Upper String / Shouting
            return "Whoa, chill out!"
        elif message.endswith("?"):        # Questions
            return "Sure."

        return "Whatever."
