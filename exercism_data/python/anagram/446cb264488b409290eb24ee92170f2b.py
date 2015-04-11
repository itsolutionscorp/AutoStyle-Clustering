def detect_anagrams(anagram, word_list):
    anagram_lower = anagram.lower()
    letters = {x for x in anagram}
    anagram_list = []

    for word in word_list:
        count = 0
        word_lower = word.lower()

        if len(word) is len(anagram) and word.lower() != anagram:
            for letter in letters:
                if anagram_lower.count(letter) is word_lower.count(letter):
                    count += 1
            if count is len(letters):
                anagram_list.append(word)
    return anagram_list
