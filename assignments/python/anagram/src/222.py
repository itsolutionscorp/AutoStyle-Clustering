import collections as c
def detect_anagrams(word, possible):
    word = word.lower()
    count = c.Counter(word)
    hits = []
    
    for word_ in possible:
        word_ = word_
        pos_count = c.Counter(word_.lower())
        
        if pos_count == count and not word == word_.lower():
            hits.append(word_)
            
    return hits
