def word_count(phrase):
    counter = {}
    for word in phrase.split():
        word = "".join(c.lower() for c in word if c.isalnum())
        if not word:
            continue
        if word in counter:
            counter[word] += 1
        else:
            counter[word] = 1
    return counter
