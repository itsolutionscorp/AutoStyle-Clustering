def detect_anagrams(word, lst):
    "Returns the anagrams for a given word from a given list."
    anagrams = []
    for wrd in lst:
    	if len(word) == len(wrd) and not word.lower() == wrd.lower():
    	    letters1 = sorted(list(word.lower()))
    	    letters2 = sorted(list(wrd.lower()))
    	    if letters1 == letters2:
    	        anagrams.append(wrd)
    return anagrams
