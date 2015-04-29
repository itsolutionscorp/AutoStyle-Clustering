def detect_anagrams(target, *words):
    result = []
    for word in words[0]:
        if len(target) != len(word) or target.lower() == word.lower():
            continue
        if sorted(list(target.lower())) == sorted(list(word.lower())):
            result.append(word)
    return result
