from collections import Counter
# decorator to use yield instead of returning a list
listify = lambda f: lambda *a, **kw: list(f(*a, **kw))
@listify
def detect_anagrams(word, suspects):
    wordl = word.lower()
    wordc = Counter(wordl)
    for suspect in suspects:
        suspectl = suspect.lower()
        if suspectl != wordl and Counter(suspectl) == wordc:
            yield suspect
