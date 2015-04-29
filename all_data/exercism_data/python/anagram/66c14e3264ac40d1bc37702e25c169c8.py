def detect_anagrams(s, words):
    s_letters = string_to_charcounts(s)

    matches = []
    for word in words:
        if word.lower() == s.lower():
            continue

        w_letters = string_to_charcounts(word)
        if (len(s_letters) == len(w_letters) and
           all(w_letters.setdefault(c, 0) == count
               for c, count in s_letters.iteritems())):
            matches.append(word)
    return matches


def string_to_charcounts(s):
    '''
    Takes a string
    Returns a dick mapping {char: char_count}
    '''
    char_counts = {}
    for letter in s.lower():
        char_counts[letter] = char_counts.setdefault(letter, 0) + 1
    return char_counts
