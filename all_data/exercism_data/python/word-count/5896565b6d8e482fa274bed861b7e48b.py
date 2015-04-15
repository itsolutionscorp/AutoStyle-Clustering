__author__ = 'citypulse-dp'


def word_count(phrase):
    lines = phrase.split('\n')
    words = [word for line in lines for word in line.split(' ')]
    words = [word.strip() for word in words if len(word.strip()) > 0]
    word_dict = {}
    for word in words:
        if word in word_dict.keys():
            word_dict[word] += 1
        else:
            word_dict[word] = 1
    return word_dict
