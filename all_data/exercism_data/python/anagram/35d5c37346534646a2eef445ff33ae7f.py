
def detect_anagrams(word, list):
    result_list = []
    for item in list:
        if len(item) == len(word):
            if isAnagram(word.lower(), item.lower()):
                result_list.append(item)

    return result_list

def isAnagram(word1, word2):
    if word1 == word2:
        return False
    zipper = zip(sorted(word1), sorted(word2))
    for i, j in zipper:
        if i != j:
            return False
    return True
