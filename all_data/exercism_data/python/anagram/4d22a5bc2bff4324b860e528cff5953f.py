def detect_anagrams(keyword, wordlist):
    """Given a keyword and a wordlist, return a list of anagrams of the keyword from the wordlist"""
    keyword = keyword.lower()
    sorted_keyword = sorted(keyword)     
    return [word for word in wordlist if ((word.lower()!=keyword) and (sorted_keyword==sorted(word.lower())))]
