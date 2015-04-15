import unicodedata
import re
class bob:

    @staticmethod
    def hey(input):
        """

        Based on the input, reply with one of four responses.

        Check the criteria in order, and respond with the first
        response that matches, or "Whatever" if none match.

        "Fine. Be that way!"
          if a regular expression search for non-whitespace does not
          find anything

        "Whoa, chill out!" 
          if all the letters are capitalized and there is at least one letter

        "Sure"
          if the input ends in a question mark

        "Whatever"
          for any other input
        """

        # Do a regex search for sequence of non-whitespace
        # Reference:  http://pymotw.com/2/re/
        p = re.compile('\S+')
        # This returns "None" if input is all whitespace
        m = p.search(input)
        if m is None:
            allWhiteSpace = True
        else:
            allWhiteSpace = False
        if allWhiteSpace:
            return "Fine. Be that way!"

        # If the input is all upper case, look through the characters
        # one at a time.
        if input == input.upper():
            for c in input:
                try:
                    # Return "Whoa, chill out!" if input has a letter
                    if unicodedata.name(c).count('LETTER') > 0:
                        return u'Whoa, chill out!'
                except ValueError:
                    pass

        # If the string ends in a question mark
        # return "Sure"
        if input[-1] == "?":
            return u'Sure.'
        
        # return the default response
        return u"Whatever."
