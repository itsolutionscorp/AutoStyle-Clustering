def detect_anagrams(from_word, word_list):
    alpha_string = ''.join(sorted(from_word.lower()))
    anagram_list = []
    for word in word_list:
        alpha_word = ''.join(sorted(word.lower()))
        if alpha_string == alpha_word and word.lower() != from_word and word not in anagram_list:
            anagram_list.append(word)

    return anagram_list
