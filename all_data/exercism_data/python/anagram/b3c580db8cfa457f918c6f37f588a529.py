class Anagram(object):
    def __init__(self, word):
        self.word = word

    def match(self, my_list):
        return [x for x in my_list if (sorted(x.lower()) == sorted(self.word.lower()) and not x == self.word)]
