from collections import Counter

def detect_anagrams(word, options):
    word = word.lower() #ignore case
    
    word_char_count = Counter(char for char in word)
    res = []
    for option in options:
        if option.lower() != word:
            option_char_count = Counter(c for c in option.lower())
            if option_char_count == word_char_count:
                res.append(option)
    return res
