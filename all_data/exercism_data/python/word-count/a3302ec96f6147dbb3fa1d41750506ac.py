class Phrase(object):


    def __init__(self, phrase_input):
        self.phrase_input = phrase_input
        self.phrase_alnum = ''
        for char in self.phrase_input:
            if char.isalnum() or char.isspace():
                self.phrase_alnum += char
        self.phrase_clean = self.phrase_alnum.lower()

    def word_count(self):

        words_all = self.phrase_clean.split()
        words_counted = {}
        
        for word in words_all:
            if word not in words_counted:
                words_counted[word] = words_all.count(word)

        return words_counted
            
            
        
        
