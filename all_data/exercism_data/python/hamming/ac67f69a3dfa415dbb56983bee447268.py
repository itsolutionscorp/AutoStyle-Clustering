#!/usr/bin/env python

def distance(strand_1, strand_2):
    diff = 0
    for i in range(len(strand_1)):
        if strand_1[i] != strand_2[i]:
            diff+=1

    return diff
