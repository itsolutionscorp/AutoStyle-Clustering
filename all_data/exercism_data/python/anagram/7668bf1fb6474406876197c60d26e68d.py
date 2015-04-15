

def detect_anagrams (word, words):
    anagrams = []
    like = True
    for w in words:
        if w.lower() == word.lower():   # if words are same continue without comparing
            continue
        popword = list(word.lower())    # create a list of lower case letters in word
        for l in w.lower():
            if not like:                # only continue if a like letter is found
                break                   # if not continue to next word
            else:
                like = False
            for x in popword:
                if x == l:
                    popword.remove(x)   # if like letter is found remove it from list
                    like = True         #    
                    break               #    then continue to next letter
        if like and not popword:
            anagrams.append(w)          # if loop ends with like=true and there is nothing in popword it an anagram
        else:
            like = True                 # reset for next word
    return anagrams
                    
                    
                    
            
