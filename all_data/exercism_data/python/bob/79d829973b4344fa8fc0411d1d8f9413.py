from __future__ import unicode_literals

# Normally this would go in a separate file and get imported.
class Conversation(object):
    ''' Conversation is created from initial words.
        Conversation allows inspection for traits like
        is this a question, is it yelling, and so on.
    '''

    def __init__(self, words):
        ''' Store the conversation for later interrogation.
            We could strip whitespace here but future
            options may need that so we'll have to keep it.
        '''
        self._words = words

    def is_question(self):
        ''' Is this a question?
            Looks for a '?' at the end of the conversation.
        '''

        # Remove trailing white space and
        # test if the end character is '?'
        words = self._words.strip()
        if words:
            return words.endswith('?')
        return False

    def is_yelling(self):
        ''' Is this yelling? '''

        # If all the letters are upper case
        # this is yelling.

        # Silence is not yelling but does not change
        # when upper cased.

        if self.is_silence():
            return False

        # Conversation may have no letters.
        # We'll not consider that yelling. We detect
        # that as when the upper case and lower case
        # versions of the words are the same.

        words = self._words.strip()
        upper_case = words.upper()
        lower_case = words.lower()

        if words == upper_case and not (upper_case == lower_case):
            return True

        return False

    def is_silence(self):
        ''' Is this silence '''

        # Silence is a string that's just whitespace
        words = self._words.strip()
        if not words:
            return True
        return False

# Typically Bob would be a class that implemented 'hey'
# and was maybe subclassed from, for example, class Teenager.
# That would allow you to create different Teenagers
# and expand on their traits.

def hey(words):
    ''' Bob's implementation of hey checks for his
        conversational triggers and responds appropirately.
    '''

    convo = Conversation(words)

    # Check silence first since it's easy and
    # silence can't be interpreted as anything else.
    if convo.is_silence():
        return "Fine. Be that way!"

    # Next, check yelling. Bob will complain
    # about yelling whether it's a question or not.
    if convo.is_yelling():
        return "Whoa, chill out!"

    # Respond to questions in a helpful manner.
    if convo.is_question():
        return "Sure."

    # Default teenage surliness.
    return "Whatever."
