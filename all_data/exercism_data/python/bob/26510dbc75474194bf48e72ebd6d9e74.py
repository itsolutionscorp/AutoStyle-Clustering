class Bob:

    def hey(self, msg):
        """Bob's response to a given message."""
        response = "Whatever."
        if shouting(msg):
            response = "Woah, chill out!"
        elif question(msg):
            response = "Sure."
        elif saying_nothing(msg):
            response = "Fine. Be that way!"
        return response


def shouting(msg):
    "A string with no lower-case characters and some upper-case characters"
    return msg.upper() == msg and msg.lower() != msg


def question(msg):
    "Question mark at the end of a string"
    try:
        return msg[-1] == "?"
    except:
        False


def saying_nothing(msg):
    "Only whitespace"
    return msg.strip() == ""
