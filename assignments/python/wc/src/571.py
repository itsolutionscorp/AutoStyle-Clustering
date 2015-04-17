def word_count(word):
    if isinstance(word, basestring) and len(word) > 0:
        words = word.split()
        counts = {}
        for a in words:
            if a in counts:
                counts[a] += 1
            else:
                counts[a] = 1
        return counts
