import re, collections
def word_count(phrase):
    words = re.findall(r'[\w.?\-\!\&\@\$\%\^\&\:",]+', phrase)
    c = collections.Counter(words)
    return c
