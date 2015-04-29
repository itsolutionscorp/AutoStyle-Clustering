import numpy as np

def detect_anagrams(word, sentence):
    
    all_s = []
    
    for i, s in enumerate(sentence):
        if len(word) == len(s):
            if np.all( np.sort(list(s.lower())) == np.sort(list(word.lower())) ):
                if word.lower() != s.lower(): 
                    all_s.append(sentence[i])
                    
    return all_s
