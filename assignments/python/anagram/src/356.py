def detect_anagrams(st, word_list):
    matches = []
    for word in word_list:
        if sorted(st.lower()) == sorted(word.lower()) and st.lower() != word.lower():
            matches.append(word)
    return list(matches)
