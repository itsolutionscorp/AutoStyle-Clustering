def detect_anagrams(word,target_list):
    n = len(word)
    lower = word.lower()
    counts = {c: lower.count(c) for c in lower}

    return [w for w in target_list if w.lower()!=word.lower() and counts=={c:w.lower().count(c) for c in w.lower()}]
