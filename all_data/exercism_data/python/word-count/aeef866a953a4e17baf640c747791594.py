def word_count(word):
    counts = {}
    # check if is a string and length > 0
    if isinstance(word, basestring) and len(word) > 0:
        words = word.split()
        for a in words:
            if a in counts:
                # increment count
                counts[a] += 1
            else:
                # add to dict
                counts[a] = 1
    return counts
