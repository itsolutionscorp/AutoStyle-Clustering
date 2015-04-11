def sort(word):
    word = word.lower()
    output = list(word)
    output.sort()
    return output
    

class Anagram():
    def __init__(self, word):
        self.word = word
        self.sorted = sort(word)
        
    def match(self, check):
        results = []
        
        for word in check:
            sortedword = sort(word)
            
            if sortedword == self.sorted and word != self.word:
                results.append(word)
            
        return results
                
