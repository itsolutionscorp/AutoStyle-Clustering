# -*- coding: utf-8 -*-
"""
Created on Fri Oct  3 22:42:21 2014

@author: luke
"""

from collections import Counter

def word_count(word):
    
   word = word.split()
   wordDic = {}
   for item in word:
       if item in wordDic:
           wordDic[item] += 1
       else:
           wordDic[item] = 1
   return wordDic
   
       
       
                        
def word_count_cheat(word):
    
     return Counter(word.split())
    
