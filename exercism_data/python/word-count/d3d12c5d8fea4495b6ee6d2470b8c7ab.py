def word_count(word=""):
    actual_w = word.strip()
    
    seen = {}
    
    for w in actual_w.split():
        if w in seen.keys():
            seen[w] += 1
        else:
            seen[w] = 1
        
    return seen
