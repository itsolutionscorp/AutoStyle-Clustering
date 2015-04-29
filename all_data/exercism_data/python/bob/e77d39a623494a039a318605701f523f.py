class Bob(object):
    """Representation of Bob, a lackadaisical teenager."""

    @staticmethod
    def _isyelling(text):
        """Return `True` if all letters in `text` are in uppercase.

        There must be at least one letter in `text`"""

        letters = [c for c in text if c.isalpha()]
        return letters != [] and all(c.isupper() for c in letters) 

    def hey(self, text):
        """Respond to the given `text`.

        There are four possible responses:
        - 'Fine. Be that way!' if `text` is empty or contains only spaces
        - 'Woah, chill out!' if there is at least one letter in `text` and 
          all letters are in uppercase
        - 'Sure.' if `text` ends in a question mark
        - 'Whatever.' for any other cases"""

        if text.replace(' ', '') == '':
            return 'Fine. Be that way!'
        elif self._isyelling(text):
            return 'Woah, chill out!'
        elif text[-1] == '?': 
            return 'Sure.'
        else:
            return 'Whatever.'
