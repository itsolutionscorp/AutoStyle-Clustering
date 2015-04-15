class Anagram(object):
    '''Represents anagram word which is used for matching.'''
    def match(self, words):
        '''Return list of words which are anagrams of current sample.'''
        return [word for word in words if self._is_anagram(word)]

    def __init__(self, sample):
        self.sample = sample.lower()

    def _is_anagram(self, word):
        return word.lower() != self.sample and \
               ''.join(sorted(word.lower())) == ''.join(sorted(self.sample))
