#
# Submission file for the Python "word-count" exercise.
#
def word_count(what):
    retdict = {}
    splitarr = what.split()
    for word in splitarr:
        if word in retdict:
            retdict[word] += 1
        else:
            retdict[word] = 1
    return retdict
