from collections import Counter


def detect_anagrams(what, words):
    root = index_word(what)
    return [w for w in words if w != what and cmp(root, index_word(w)) == 0]


def index_word(what):
    idx = Counter()
    idx.update(str.upper(what))
    return idx