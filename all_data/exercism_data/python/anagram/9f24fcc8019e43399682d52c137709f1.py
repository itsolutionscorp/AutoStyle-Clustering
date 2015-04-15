class Anagram(object):
    def __init__(self, word):
        self.original = word.lower()
        self.alphagram = alphagram(word)

    def match(self, words):
        return [word for word in words
                if alphagram(word) == self.alphagram and word != self.original]


def alphagram(string):
    return ''.join(sorted(string.lower()))
