__author__ = 'jakesawyer'
import string
def word_count(str):
  word_count_dict = {}
  words = str.split(" ")
  counts = []
  count_pos = 0
  for word in words:
    current_count = 0
    for i in range(len(words)):
      counts.append(0)
      if word == words[i]:
        current_count = current_count + 1
        counts[count_pos] = current_count
    count_pos+=1
  for i in range(len(words)):
    word_count_dict["%s" % words[i]] = counts[i]
  return word_count_dict
