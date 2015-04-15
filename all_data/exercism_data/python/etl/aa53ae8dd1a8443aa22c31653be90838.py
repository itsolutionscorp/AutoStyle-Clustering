#etl.py
#eta:0
__author__ = 'greg'
import string


def transform(old_data):
    new_data = {}
    for k in old_data.keys():
        for i in range(0, len(old_data[k])):
            new_data[string.lower(old_data[k][i])] = k
    return new_data
