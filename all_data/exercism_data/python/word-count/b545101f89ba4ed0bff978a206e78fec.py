def word_count(phrase):
    count = {}
    words = phrase.strip().split()
    for word in words:
        if word in count.keys():
            count[word] = count[word] + 1
        else:
            count[word] = 1
    return count 
