class Phrase(object):
    def __init__(self, string):
        self.string = string

    def word_count(self):
        words = self.string.translate(None, '!@$%^&,.:').lower().split()

        word_counts = {}
        for word in words:
            if word != '':
                if word not in word_counts:
                    word_counts[word] = 0
                word_counts[word] += 1

        return word_counts
