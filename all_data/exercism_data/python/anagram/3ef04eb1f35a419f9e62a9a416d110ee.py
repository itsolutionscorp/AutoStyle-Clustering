from collections import Counter

def anagram_compare(keyword, keyword_counter, word):
    """Given a keyword (all lowercase) and a Counter object for that keyword, tests to see if a word is an anagram for the keyword"""
    word = word.lower()
    return ((word != keyword) and (Counter(word) == keyword_counter))

def detect_anagrams(keyword, wordlist):
    """Given a keyword and a wordlist, finds anagrams of the keyword in the wordlist"""
    #First, get the keyword into lowercase and then establish a Counter for the keyword to count the letters of the word
    keyword = keyword.lower()
    keyword_counter = Counter(keyword)
    
    #With that established, we can filter the given wordlist with our helper function
    return [word for word in wordlist if anagram_compare(keyword, keyword_counter, word)]
