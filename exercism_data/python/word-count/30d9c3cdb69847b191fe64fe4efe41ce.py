def word_count(input):
    """Count occurence of words in input string"""
    count = {}
    words = input.split()
    for w in words:
        if w in count:
            count[w] += 1
        else:
            count[w] = 1
    return count
