__author__ = 'tracyrohlin'


def word_count(s):
    split_s = s.split()
    end_count = {}
    for item in split_s:
        if item in end_count:
            end_count[item] = end_count[item]+1
        else:
            end_count[item] = 1
    return end_count
