from re import split
def word_count(text):
    words = split('[ \n\t]+', text)
    return dict([(x, words.count(x)) for x in words])
