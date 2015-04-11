from string import lower

def detect_anagrams(test_word, candidates):
    return [word for word in candidates if is_anagram(lower(test_word), lower(word))]

def is_anagram(word1, word2):
    if len(word1) != len(word2):
        return False
    elif word1 == word2:
        return False
    else:
        word1_list = list(word1) 
        for c in word2:
            if word1_list.count(c) > 0:
                word1_list.remove(c)
            else:
                return False
        return True
