#!/usr/bin/python

class Anagram:
   def __init__(self, word):
      self.word = word.lower()
   
   def match(self, anagram_list):
      sorted_word = sorted(self.word)
      matches = []
      
      for a in anagram_list: 
         if a == self.word: 
            continue 
         
         if sorted(a.lower()) == sorted_word:
            matches.append( a )

      return matches
