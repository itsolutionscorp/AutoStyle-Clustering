#!/usr/bin/python
import re

def word_count(phrase):
  # Split the given phrase into a list of words
  word_list = phrase.split(" ")
  word_dict = {}

  for word in word_list:
    # Remove all non-alpha numeric characters from the word
    word = re.sub('[^A-Za-z0-9]+', '', word)
    # Increment count of word if it exists in the dictionary already
    if word.lower() in word_dict:
      word_dict[word.lower()] = word_dict[word.lower()] + 1
    # Word does not in the dictionary yet so initialize it if it is not an empty string
    elif word.isalnum():
      word_dict.setdefault(word.lower(), 1)

  return word_dict
