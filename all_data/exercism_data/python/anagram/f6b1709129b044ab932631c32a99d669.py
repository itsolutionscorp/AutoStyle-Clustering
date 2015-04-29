class Anagram(object):
    def __init__(self, sample):
        self.sample = sample.lower()

    def match(self, words):
        return [word for word in words if word.lower() != self.sample and ''.join(sorted(word.lower())) == ''.join(sorted(self.sample))]
