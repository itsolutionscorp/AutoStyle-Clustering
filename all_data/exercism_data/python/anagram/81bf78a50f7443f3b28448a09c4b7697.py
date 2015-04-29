def detect_anagrams(word, *args):
    anagrams =[]
    word =word.lower()
    for w in args:
        w =str(w).lower()
        w =w.split()
        if len(word) == len(w):
            if sorted(word) == sorted(w):
                anagrams.append(w)
    return anagrams

# For some reason it fails oddly
# Ran 9 tests in 0.001s
#
# FAILED (failures=5)
