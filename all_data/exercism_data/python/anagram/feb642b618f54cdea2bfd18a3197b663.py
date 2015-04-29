def detect_anagrams(orig_key, words):
    key = sortWord(orig_key)
    
    anagrams = []
    for word in words:
        if sortWord(word) == key:
            if word.lower() <> orig_key:
                anagrams.append(word)
    
    return anagrams

def sortWord(word):
    return "".join(sorted(word.lower()))
