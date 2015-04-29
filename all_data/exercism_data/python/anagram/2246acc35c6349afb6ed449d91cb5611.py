def letters_dict(word):
    word = word.lower()
    result = dict()
    for i in range(0, len(word)):
        if word[i] in result:
            result[word[i]] += 1
        else:
            result[word[i]] = 1
    return result

def detect_anagrams(base, ans):
    base = base.lower()
    base_dict = letters_dict(base)
    ans_dicts = [letters_dict(x) for x in ans]
    return [ans[i] for i in range(0, len(ans))
                   if base_dict == ans_dicts[i] and base != ans[i].lower()]
