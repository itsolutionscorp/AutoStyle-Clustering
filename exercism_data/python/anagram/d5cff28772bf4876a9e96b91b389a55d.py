

def detect_anagrams (word, words):
    return [w for w in words if word.lower() != w.lower() and sorted(w.lower()) == sorted(word.lower())]
                    
            
