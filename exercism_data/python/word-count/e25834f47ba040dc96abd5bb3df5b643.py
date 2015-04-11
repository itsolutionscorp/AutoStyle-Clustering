#
# Write a program that given a phrase can count the occurrences of each word in that phrase.
#
# For example for the input `"olly olly in come free"`
#
# ```plain
# olly: 2
# in: 1
# come: 1
# free: 1
# ```
#

def word_count(phrase):

    words = phrase.split()
    
    cnt = dict()

    for word in words:
        if word in cnt:
            cnt[word] += 1
        else:
            cnt[word] = 1

    return cnt
