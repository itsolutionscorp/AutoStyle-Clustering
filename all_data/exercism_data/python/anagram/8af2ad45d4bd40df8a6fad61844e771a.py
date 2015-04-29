def detect_anagrams(anagram, word_list):
    return [word for word in word_list if is_anagram(anagram, word)]
    
def is_anagram(anagram, word):
    anagram = anagram.lower()
    word = word.lower()
    if word == anagram: 
        return False
    else:
        return count_letters(anagram) == count_letters(word)

def count_letters(word):
    return {letter: word.count(letter) for letter in word}
