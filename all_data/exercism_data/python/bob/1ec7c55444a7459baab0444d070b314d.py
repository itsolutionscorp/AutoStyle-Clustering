class Bob:
    "A lackadaisical teenager"

    def hey(self, says_the_other):
        "Bob's response to anything"
        if not says_the_other or says_the_other.isspace():
            return 'Fine. Be that way!'
        elif self.is_yelling(says_the_other):
            return 'Woah, chill out!'
        elif says_the_other[-1] == '?':
            return 'Sure.'
        return 'Whatever.'

    @staticmethod
    def is_yelling(text):
        "Determines whether text contains yelling or not"
        letters = ''.join([c for c in text if c.isalpha()])
        if letters and letters.isupper():   # YELLING
            return True
        if text[-1] == '!':
            if isinstance(text, unicode):   # Just a tourist
                return False
            if "make out" in text:  # Sounds like a nice proposition!
                return False
            return True     # The exclamation mark marks the end of yelling!
        return False
