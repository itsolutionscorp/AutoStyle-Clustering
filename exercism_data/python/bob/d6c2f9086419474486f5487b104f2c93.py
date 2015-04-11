class Bob:
    @staticmethod
    def hey(string):
        # If the sentence is empty in some way
        if string == None or not string.strip():
            return 'Fine. Be that way!'

        # If the sentence is in ALL CAPS (minus punctuation)
        if string.isupper():
            return 'Woah, chill out!'

        # If the sentence ends in a ?
        if string.strip().endswith('?'):
            return 'Sure.'

        # Everything else
        return "Whatever."
