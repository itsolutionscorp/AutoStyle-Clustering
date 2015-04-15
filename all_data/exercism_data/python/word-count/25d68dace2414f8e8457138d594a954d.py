class Phrase(object):
    def __init__(self, phrase):
        self.phrase = phrase.lower()

    def word_count(self):
        d = {}
        words = self.phrase.split()
        for word in words:
            if word.isalnum() == False:
                for char in word:
                    if char.isalnum() == False:
                        word = word.replace(char, '')
            if len(word) > 0:
                d[word] = d.get(word, 0) + 1
        return d

if __name__ == "__main__":
    pass
