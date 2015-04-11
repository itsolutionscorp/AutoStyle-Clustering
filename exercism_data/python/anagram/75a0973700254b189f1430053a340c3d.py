def is_anagram(input1, input2):
    list1 = list(input1.lower())
    list2 = list(input2.lower())

    if list1 == list2:
        return False

    list1.sort()
    list2.sort()
    return list1 == list2


def detect_anagrams(word, candidates):
     return [x for x in candidates if is_anagram(word, x)]
