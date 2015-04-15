class Bob(object):
    """Representation of Bob, a lackadaisical teenager."""

    def hey(self, text):
        """Respond to the given `text`.

        There are four possible responses:
        - 'Fine. Be that way!' if `text` is empty or contains only spaces
        - 'Woah, chill out!' if there is at least one letter in `text` and 
          all letters are in uppercase
        - 'Sure.' if `text` ends in a question mark
        - 'Whatever.' for any other cases

        """

        if not text or text.isspace():
            return 'Fine. Be that way!'
        elif text.isupper():
            return 'Woah, chill out!'
        elif text.endswith('?'): 
            return 'Sure.'
        else:
            return 'Whatever.'
