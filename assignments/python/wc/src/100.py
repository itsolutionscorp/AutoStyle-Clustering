def word_count(sentence):
    words = sentence.split()
    word_counts = {}
    for word in words:
        try:
            word_counts[word]+=1
        except KeyError:
            word_counts[word]=1
    return word_counts
