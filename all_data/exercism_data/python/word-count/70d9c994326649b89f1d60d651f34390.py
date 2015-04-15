__author__ = 'oniwa'


class Phrase(object):
    """ Phrase class """
    def __init__(self, phrase):
        self.msg = phrase

    def word_count(self):
        """ Counts the number of unique words in a phrase"""
        wordcount = {}
        wordlist = self.msg.split()

        for item in wordlist:
            # Remove all punctuation
            item = item.translate(None, ' ,./?;:\'\\"|]}[{!@#$%^&*()-_=+`~').lower()

            # Check if item is a letter or number
            if item.isalpha() or item.isdigit():
                # If item is a key increment value
                if item in wordcount:
                    wordcount[item] += 1
                # Else make item a key with value of 1
                else:
                    wordcount[item] = 1

        return wordcount

if __name__ == "__main__":
    print Phrase('testing, 1, 2 testing').word_count()
