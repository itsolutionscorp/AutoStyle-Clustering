import string

def word_count(phrase):
    word_counts = {}
    words = phrase.split()
    exclude = set(string.punctuation)
    for word in words:
        word = word.lower()
        word = ''.join(ch for ch in word if ch not in exclude)
        if word != '':
            if word in word_counts:
                word_counts[word] += 1
            else:
                word_counts[word] = 1
    return(word_counts)
