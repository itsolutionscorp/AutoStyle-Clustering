def word_count(text):
    counts= {}
    sentences = text.split("\n")
    for sentence in sentences:
        words = sentence.split(" ")
        for word in words:
            if word == '':
                continue
            if word in counts:
                counts[word] += 1
            else:
                counts[word] = 1
    return counts
