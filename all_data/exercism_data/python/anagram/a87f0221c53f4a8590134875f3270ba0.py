def detect_anagrams(word,candidates):
    
    def is_anagram(word,candidate_in_list):
        ''' 
        helper function that returns True if word and candidate_in_list are
        anagrams
        ''' 
        word_dict = {}
        candidate_dict = {}
        
        if word.lower() == candidate_in_list.lower():
            return False
        
        for char in word :
            word_dict[char.lower()] = word_dict.get(char.lower(),0)+1
        for char in candidate_in_list :
            candidate_dict[char.lower()] = candidate_dict.get(char.lower(),0)+1
        return word_dict == candidate_dict
        
        
    anagram_list = []    
    for item in candidates:
        if is_anagram(word,item):
            anagram_list.append(item)
    return anagram_list    
    
