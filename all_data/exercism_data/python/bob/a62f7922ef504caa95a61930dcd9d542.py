__author__ = 'Greg'


class SpeechSorter(object):
    """
    Sorts strings (entered_text) by interpreting their meaning, as specified by
    method name. Returns Boolean value. 
    """

    def __init__(self, entered_text):
        self.entered_text = entered_text

    def gibberish(self):
        """
        True if entered_text is an empty string, False for any other value.
        """
        
        return self.entered_text.strip()  == "" # empty string after stripping

    def yelling(self):
        """
        True if all characters in entered_text are capitalized, False if any 
        characters are not.
        """
        
        return self.entered_text.isupper() # capslock'ed

    def question(self):
        """
        True if entered_text ends with a '?', False otherwise.
        """
        
        return self.entered_text.endswith('?') 


def hey(sentence):
    """
    Uses class SpeechSorter() to sort the parameter, a string. Returns 1 string
    according to which if/elif statement is satisfied first.
    """
    
    bob = SpeechSorter(sentence)
    
    if bob.gibberish():
        return "Fine. Be that way!"
    
    elif bob.yelling():
        return "Woah, chill out!"
    
    elif bob.question():
        return "Sure."
    
    else:
        return "Whatever."
