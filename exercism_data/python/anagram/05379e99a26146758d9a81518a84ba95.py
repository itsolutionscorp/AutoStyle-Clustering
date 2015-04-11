__author__ = 'oniwa'


class Anagram(object):
    def __init__(self, msg):
        self.msg = msg

    def match(self, l_anagrams):
        matches = []

        for nagaram in l_anagrams:
            if len(self.msg) == len(nagaram) and self.msg != nagaram:
                if self._char_count(self.msg) == self._char_count(nagaram):
                        matches.append(nagaram)

        return matches

    def _char_count(self, word):
        """ Counts the number of unique words in a phrase"""
        charcount = {}
        charlist = []
        for char in word:
            charlist.append(char)

        for item in charlist:
            # Remove all punctuation
            item = item.translate(None, ' ,./?;:\'\\"|]}[{!@#$%^&*()-_=+`~').lower()

            # Check if item is a letter or number
            if item.isalpha() or item.isdigit():
                # If item is a key increment value
                if item in charcount:
                    charcount[item] += 1
                # Else make item a key with value of 1
                else:
                    charcount[item] = 1

        return charcount

if __name__ == "__main__":
    print Anagram('Orchestra').match('cashregister Carthorse radishes'.split())
    print Anagram('Word')._char_count('hello')
