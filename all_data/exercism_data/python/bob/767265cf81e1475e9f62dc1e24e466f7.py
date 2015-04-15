def hey(message):

    if message.isspace() or message == "":
        return "Fine. Be that way!"
    else:

        # Returns true if all cased characters are upper
        if message.isupper():
            return "Whoa, chill out!"

        if message.endswith('?'):
            return "Sure."

        return "Whatever."
