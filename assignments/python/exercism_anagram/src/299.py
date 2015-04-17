def detect_anagrams(word1,wordlist):
    '''
    Return True if anagram of word1 found in wordList else False
    :param word1: String
    :param wordlist: List of Strings
    :return: bool
    '''
    # pick the words with equal length and not equal to word
    newlist =[x for x in  wordlist if len(word1) == len(x) and x.lower() != word1.lower()]
    # make a copy
    newlist2 = [idx.lower() for idx in newlist]
    # remove all characters of word1 from all other words
    for c in word1.lower():
        newlist2 = [word.replace(c,'',1) for word in newlist2]
    # return words whose length became zero
    
    return [newlist[idx] for idx in range(len(newlist2)) if len(newlist2[idx])==0]
#print (detect_anagrams('ant', 'tan stand at'.split()))
#print (detect_anagrams('banana', ['banana']))
