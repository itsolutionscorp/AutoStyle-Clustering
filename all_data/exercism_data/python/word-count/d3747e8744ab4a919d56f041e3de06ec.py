class Phrase:
    def __init__(self, phrase):
        phrase = phrase.lower() + '-'
        words = {}

        word = None
        for ch in phrase:
            if ch.isalnum():
                if word is None:
                    word = ''
                word += ch
            else:
                if word is not None:
                    if word not in words:
                        words[word] = 1
                    else:
                        words[word] += 1
                    word = None
        self.words = words

    def word_count(self):
        return self.words
            
