def word_count(phrase):
    freq = {}
    newstring = ' '.join(phrase.split())
    words = newstring.split(" ")
    for i in words :
        freq[i] = words.count(i)
    return freq
