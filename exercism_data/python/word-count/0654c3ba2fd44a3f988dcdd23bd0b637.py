class Phrase:
    def __init__(self, phrase):
        self.phrase = phrase.strip().lower().split()

    def word_count(self):
        word_dict = {}
        for word in self.phrase:
            word_input = ''.join(e for e in word if e.isalnum()).strip()
            if word_input:
                word_dict[word_input] = word_dict.get(word_input, 0) + 1
        return word_dict
        
