def word_count(phrase):
    phrase = phrase.strip()
    words = phrase.split()
    count = {}
    for word in words:
        try:  # increment word count
            count[word] += 1
        except KeyError:  # first encounter
            count[word] = 1
    return count
