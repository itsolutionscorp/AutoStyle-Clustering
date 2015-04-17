# Anagram
def detect_anagrams(word, word_list):
    result = []
    sorted_word = sorted(word.lower())
    
    for possible_anagram in word_list:
        if sorted(possible_anagram.lower()) == sorted_word \
            and possible_anagram.lower() != word.lower():
            result.append(possible_anagram)
    return result
