import re


class Bob(object):

    def hey(self, message):
        if message:
            # prattling
            if "!" in message and "?" in message:
                return "Sure."
            # presence of a digit; numeric question
            elif re.search(r'\d+', message) and message.endswith("?"):
                return "Sure."

            # if the message is capitalized and ends question mark
            elif message.endswith("?") and message.isupper():
                return "Woah, chill out!"

            # message is capitalized and ends with an exclamation mark
            elif message.endswith("!") and message.isupper():
                return "Woah, chill out!"

            # message that is capitalized and ends with an exclamation mark
            elif message.isupper() and not message.endswith("!"):
                return "Woah, chill out!"

            elif message.endswith("?"):
                return "Sure."

            # shouting numbers and test if at least there are digits
            elif len(re.findall("\d+", message)) > 1 and message.endswith("!"):
                return "Woah, chill out!"

            # check to see that the contents are filled out (unempty strings)
            elif len(message.split()) == 0:
                return "Fine. Be that way!"

            else:
                return "Whatever."
        else:
            if message is None:
                return "Fine. Be that way!"
            elif message == "":
                return "Fine. Be that way!"
