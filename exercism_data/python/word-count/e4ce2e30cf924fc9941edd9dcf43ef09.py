class Phrase():

    def __init__(self, sentence):
        self.sentence = sentence.lower()
        self.get_real_words_from_sentence()
        self.initialise_word_list()
    
    def get_real_words_from_sentence(self):
        self.words = []
        for word in self.sentence.split():
            word = self.remove_nonvalid_characters(word)
            if word:
                self.words.append(word)
    
    def remove_nonvalid_characters(self, word):
        nonvalid_chars = self.compute_non_alphanumeric_characters()
        return word.translate(None, nonvalid_chars)
    
    def compute_non_alphanumeric_characters(self):
        return ''.join(c for c in map(chr, range(256)) if not c.isalnum())

    def initialise_word_list(self):
        self.word_list = {}
        for word in self.words:
            if not self.word_list.has_key(word):
                self.word_list[word] = 0
    
    def word_count(self):
        for word in self.words:
            self.word_list[word] = self.word_list[word] + 1
        return self.word_list
    
