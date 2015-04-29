class Bob:
    @staticmethod
    def hey(string):
        # If the sentence is empty in some way
        if string == None or len(string.strip()) == 0:
            return 'Fine. Be that way!'

        # If the sentence is in ALL CAPS (minus punctuation)
        if string == string.upper():
            return 'Woah, chill out!'

        # If the sentence ends in a ?
        if string.strip()[-1] == '?':
            return 'Sure.'

        # Everything else
        return "Whatever."
