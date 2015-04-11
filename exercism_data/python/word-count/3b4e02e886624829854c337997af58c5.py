class Phrase():
    def __init__(self, phrase):
        chars = []

        for ch in phrase:
            if ch.isalpha() or ch.isdigit() or ch == " ":
                chars.append(ch)
                
            else:
                pass
            
        
        self.phrase = "".join(chars)
        self.phrase = self.phrase.lower()
        
        self.words = self.phrase.rsplit()
        

    def word_count(self):
        wordset = set(self.words)

        wordcount = {}

        for x in wordset:
            wordcount[x] = self.words.count(x)

        return wordcount
            
