class Anagram(object):
    '''Represents anagram word which is used for matching.'''
    def __init__(self, sample):
        self.sample = sample.lower()

    def match(self, words):
        '''Return list of words which are anagrams of current sample.'''
        return [word for word in words if word.lower() != self.sample and ''.join(sorted(word.lower())) == ''.join(sorted(self.sample))]
