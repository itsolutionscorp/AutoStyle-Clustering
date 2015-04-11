class Bob:
    """
    Bob is a lackadaisical teenager.
    """
    def hey(self, text):
        """
        Returns Bob's response when addressed with text.
        Return 'Sure.' if you ask him a question,
        'Woah, chill out!' if the text is all caps,
        'Fine. Be that way!' if you address him without actually saying anything,
        and 'Whatever.' to anything else.
        """
        if text is None or text.strip() == '':
            return 'Fine. Be that way!'
        elif text.isupper():
            return 'Woah, chill out!'
        elif text.endswith('?'):
            return 'Sure.'
        return 'Whatever.'
