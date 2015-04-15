# -*- coding: utf-8 -*-

def word_count(sentence):
  final_list = {}

  for x in sentence.replace('\n', ' ').split(' '):

    word = x.strip()

    if not word:
      continue

    if word in final_list:
      final_list[word] += 1
    else:
      final_list[word] = 1

  return final_list
