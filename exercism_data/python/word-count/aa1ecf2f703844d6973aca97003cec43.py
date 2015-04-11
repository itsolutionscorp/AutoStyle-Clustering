import string

def word_count(phrase):
    word_counts = {}
    words = [word.lower() for word in remove_punctuation(phrase).split()]
    for word in words:
        if word not in word_counts.keys():
            word_counts[word] = 1
        else:
            word_counts[word] += 1
    return word_counts

def remove_punctuation(s):
    # Takes a string and returns it with all punctuation removed
    return s.translate(string.maketrans("",""), string.punctuation)
