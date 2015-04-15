class Anagram(object):
    """class for doing stuff with anagrams"""
    def __init__(self, source_word):
        """source_word is the word we're comparing against in match"""
        self.source_word = source_word

    def match(self, words):
        """For the supplied list of words, will return list elements that are
           anagrams of self.source_word"""
        matches = []
        for word in words:
            if word == self.source_word:
                '''skip it if anagram and src are the same'''
                continue
            '''We use x and y here, because it's a throwaway comparison'''
            x = [x.lower() for x in word]
            y = [y.lower() for y in self.source_word]
            if sorted(x) == sorted(y):
                '''We have the same letters in both words'''
                matches.append(word)
        return matches
