#!/usr/bin/env python

def transform(old_data):
    new_data = {}
    for score in old_data.keys():
        for word in old_data[score]:
            new_data[word.lower()] = score
    return new_data
