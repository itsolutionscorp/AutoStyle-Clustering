class Bob():
    # Method: hey
    #  Arguments:  self      - 
    #              respondTo - String value that a person says to "bob".
    #  Returns: Bob's response.
    def hey(self, respondTo):
        # Respond 'Fine. Be that way!' if respond to is blank or empty
        if not respondTo or len(respondTo.strip()) == 0:
            return "Fine. Be that way."
        # Respond 'Woah, chill out!' if being "yelled" at (all text is in uppercase.
        elif respondTo.upper() == respondTo:
            return "Woah, chill out!"
        # Respond 'Sure', if asked a question.
        elif respondTo[-1] == "?":
            return "Sure."
        else:
            return 'Whatever.'
