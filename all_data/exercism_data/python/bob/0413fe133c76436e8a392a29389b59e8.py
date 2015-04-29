"""Bob: a lackadaisical teenager.

Bob is a lackadaisical teenager. In conversation, his responses are very
limited. Bob answers 'Sure.' if you ask him a question. He answers 'Woah, chill
out!' if you yell at him. He says 'Fine. Be that way!' if you address him
without actually saying anything. He answers 'Whatever.' to anything else.
"""


class Sentence(object):
    """A sentence classifier."""

    @staticmethod
    def is_shouting(sentence):
        """Return true if a sentence is shouting.

        A sentence is shouting if all cased characters in the sentence are
        uppercase and there is at least one cased character.
        """
        return sentence.isupper()

    @staticmethod
    def is_interrogative(sentence):
        """Return true if a sentence is interrogative.

        A sentence is interrogative if the sentence ends with "?" and does not
        consist of only uppercase characters.
        """
        sentence = sentence.strip()
        return sentence.endswith('?') and (not sentence.isupper())

    @staticmethod
    def is_silent(sentence):
        """Return true if a sentence is silent.

        A sentence is silent if the sentence consists of zero or more
        whitespace characters.
        """
        return (not sentence) or sentence.isspace()


class Bob(object):
    """A lackadaisical teenager."""

    def answer(self, sentence):
        """Answer a sentence."""
        if Sentence.is_shouting(sentence):
            return 'Woah, chill out!'
        elif Sentence.is_interrogative(sentence):
            return 'Sure.'
        elif Sentence.is_silent(sentence):
            return 'Fine. Be that way!'
        else:
            return 'Whatever.'

    hey = answer
