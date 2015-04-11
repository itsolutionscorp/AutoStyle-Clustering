def detect_anagrams(word, given_list):
    results = []
    for item in given_list:
        if anagram_check(word, item) == 0:
            results.append(item)
    return results

def anagram_check(orig_word, new_word):
    if orig_word.lower() == new_word.lower():
        return -1

    return cmp([x for x in ''.join(sorted(orig_word.lower()))],
                [x for x in ''.join(sorted(new_word.lower()))])
