def word_count(word):
    # check if is a string and length > 0
    if isinstance(word, basestring) and len(word) > 0:
        words = word.split()
        counts = {}
        for a in words:
            if a in counts:
                # increment count
                counts[a] += 1
            else:
                # add to dict
                counts[a] = 1
        return counts
