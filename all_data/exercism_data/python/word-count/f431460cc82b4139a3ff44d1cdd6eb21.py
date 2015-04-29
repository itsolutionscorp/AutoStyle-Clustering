#!/usr/bin/python env

def word_count(phrase):
  words = {}
  separate = phrase.split()
  for i in separate:
    if i in words:
      words[i] += 1 
    else:
      words[i] = 1
  return words
