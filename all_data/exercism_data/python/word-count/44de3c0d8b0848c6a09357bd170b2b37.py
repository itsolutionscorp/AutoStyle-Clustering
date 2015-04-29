import string

class Phrase(object):
    def __init__(self,phrase):
        self.phrase = phrase

    def word_count(self):
        phrase = self.phrase
        exclude = list(string.punctuation)
        phrase = ''.join(ch for ch in phrase if ch not in exclude)
        freqs = {} 
        for word in phrase.lower().split():
            freqs[word] = freqs.get(word,0) + 1
        return freqs

            
