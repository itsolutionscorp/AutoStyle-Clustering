import collections
import itertools

def detect_anagrams(word, candidates):
    anagrams = []  # Collects found anagrams

    counts = collections.Counter(word.lower())

    for candidate in candidates:
        # Equal words are not anagrams
        if candidate.lower() == word.lower():
            continue

        add = True
        counts_tmp = collections.Counter(candidate.lower())
        for pair in itertools.izip_longest(counts.viewitems(),
                                           counts_tmp.viewitems()):
            if pair[0] != pair[1]:
                add = False
        if add:
            anagrams.append(candidate)

    return anagrams
