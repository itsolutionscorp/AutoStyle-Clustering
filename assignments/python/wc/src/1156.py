__author__ = 'Adam'
def word_count(input):
    assert isinstance(input, str)
    loggedwords = {}
    for i in input.replace("\n", " ").split(" "):
        if i.isspace() or len(i) == 0:
            continue
        if not i in loggedwords:
            loggedwords[i] = 1
        else:
            loggedwords[i] += 1
    return loggedwords
