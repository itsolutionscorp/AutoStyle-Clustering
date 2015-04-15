class Bob(object):
    def __init__(self):
        pass

    def _is_yelled(self, word):
        '''
        Description: if the characters in the input word are mostly capital,
        return True.  Else, return False
        Note: first, get rid of the characters that's not a letter
        '''

        return word == word.upper() and word != word.lower()

    def _is_asked(self, word):
        '''
        return True if being asked a question
        '''

        return word.endswith('?')

    def _is_greeted_without_words(self, word):
        '''
        return True if being greeted, but didn't say anything
        '''

        return (word is None) or (word.strip() == '')

    def hey(self, word):

        # remove starting space, until leaving one space:
        while word.startswith(' '):
            new_word = word[1:]
            word = new_word
            if word == ' ':
                break

        if self._is_yelled(word):
            return 'Woah, chill out!'
        elif self._is_asked(word):
            return 'Sure.'
        elif self._is_greeted_without_words(word):
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'
