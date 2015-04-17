from collections import Counter
def detect_anagrams(searchWord, searchSet):
	
    #Construct counter instance from lowercase anagram search word
    counterWord = Counter(searchWord.lower())
    #Initialize a list to hold anagram matches
    anagramList = []
    
    #Iterate through list of words to check if they are anagrams
    for each in searchSet:
        
        '''
        Compare lowercase Counter instance from line 6 to lowercase Counter instance of
        'each' AND check to make sure lowercase searchWord doesn't equal
        the lowercase 'each'. If the evaluation returns true, append 'each' to the anagram
        list. 
        '''
        if counterWord == Counter(each.lower()) and searchWord.lower() != each.lower():
            anagramList.append(each)
    return anagramList
