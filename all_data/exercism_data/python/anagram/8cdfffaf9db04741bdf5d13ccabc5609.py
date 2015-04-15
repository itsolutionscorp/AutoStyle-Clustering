def detect_anagrams(word, word_list):
    lret = []
    word = word.lower()
    word_set = set(word)
    for ana in word_list:
        ana_set = set(ana.lower())
        if len(ana) != len(word) or ana.lower() == word or ana_set != word_set:
            continue
        if ["no" for x in word_set if word.count(x) != ana.lower().count(x)]:
            continue
        lret.append(ana)
    return lret
