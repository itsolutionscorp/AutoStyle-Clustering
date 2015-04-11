# -*- coding:utf-8 -*-

def transform(old):
    ans = {}
    for key in old.keys():
        ans.update({value.lower():key for value in old[key]})
    return ans
