#
# Returns all anagrams of a word from a list of possible anagrams
#

def detect_anagrams(word, pool):
    word = word.lower()
    pool = [i for i in pool if i.lower() != word]
    possible = [i for i in pool if set(word) == set(i.lower())]
    letter_count = {i:word.count(i) for i in word}

    anagrams = []
    for i in possible:
        if {j:i.lower().count(j) for j in i.lower()} == letter_count:
            anagrams.append(i)

    return anagrams
