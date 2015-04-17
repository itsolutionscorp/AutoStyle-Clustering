import string
def word_count(input):
    words=input.translate(None, string.punctuation).lower().split()
    list=dict()
    for word in words:
        if word in list:
            list[word] += 1
        else:
            list[word] = 1
    return list
