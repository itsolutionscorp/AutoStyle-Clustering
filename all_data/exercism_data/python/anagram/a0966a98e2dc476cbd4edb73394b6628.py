def detect_anagrams(match_word, match_word_list):
    anagram_list = []
    for word in match_word_list:
        if len(word) == len(match_word) and word.lower() != match_word.lower():
            if ''.join(sorted(word.lower())) == ''.join(sorted(match_word.lower())):
                anagram_list.append(word)

    return anagram_list
