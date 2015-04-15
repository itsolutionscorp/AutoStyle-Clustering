def detect_anagrams(word, words_list):
    word_dict = make_word_dict(word)
    anagram_list = []
    for element in words_list:
        element_dict = make_word_dict(element)
        if element.lower() == word.lower():
            continue
        elif element_dict == word_dict:
            anagram_list.append(element)
    return anagram_list

def make_word_dict(word):
    letter_list = [letter.lower() for letter in word]
    letter_dict = {letter: letter_list.count(letter) for letter in letter_list}
    return letter_dict
