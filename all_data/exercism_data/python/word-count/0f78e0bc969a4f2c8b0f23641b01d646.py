class Phrase(object):

    def __init__(self, phrase_input):
        self.phrase_input = phrase_input
        self.phrase_clean = ''.join(filter(lambda x: x.isalnum() or x.isspace(), self.phrase_input)).lower()


    def word_count(self):

        import collections

        words_all = self.phrase_clean.split()
        words_counted = collections.Counter(words_all)

        return words_counted
