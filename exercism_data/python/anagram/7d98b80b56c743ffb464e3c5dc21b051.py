class Anagram(object):

    def __init__(self, original_string):
        self.original_string = original_string
        self.sorted_string = ''.join(sorted(original_string.lower()))
        pass

    def match(self, words):
        return [word for word in words \
                if ''.join(sorted(word.lower())) == self.sorted_string and \
                word.lower() != self.original_string]
