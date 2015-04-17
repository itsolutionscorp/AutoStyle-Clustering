import string
def word_count(input):
    words=input.lower() \
               .translate(None, string.punctuation) \
               .split()
    list=dict()
    for word in words:
        if word in list:
            list[word] += 1
        else:
            list[word] = 1
    return list
