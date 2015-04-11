# -*- coding: utf-8 -*-

def distance(src1, src2):
    ham = 0
    if len(src1) != len(src2):
        return ham

    for i in range(len(src1)):
        if src1[i] != src2[i]:
            ham += 1

    return ham
