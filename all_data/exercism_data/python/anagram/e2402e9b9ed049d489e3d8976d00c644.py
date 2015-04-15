def convert_to_pos(word):
    freq = [0 for x in range(97,123)]
    for char in word:
        freq[ord(char) - 97] += 1
    return freq

def detect_anagrams(base_word,possible_anagrams):
    base_freq = convert_to_pos(base_word.lower())
    base_set = set(base_word.lower()) 

    anagrams = []
    
    for possible_anagram in possible_anagrams:    
        for_processing = possible_anagram.lower()
	if len(base_word) != len(possible_anagram):
            pass 
        elif base_word == for_processing:
            pass
	elif convert_to_pos(for_processing) != base_freq or \
			set(for_processing) != base_set:
            pass
        else:
            anagrams.append(possible_anagram)
    return anagrams
