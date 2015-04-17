#!/usr/bin/python
import pdb
def detect_anagrams(base_word, word_list):
    success_list = []
    for word in word_list:
        success = True
        if len(word) != len(base_word)\
           or len(word) == 0 \
           or word.lower() == base_word.lower():
            continue
        else:
            word_char_list = list(word.lower())
            base_word_char_list = list(base_word.lower())
            for base_word_char in base_word_char_list:
                try:
                    word_char_list.pop(word_char_list.index(base_word_char))
                except ValueError:
                    success = False
                    break
            if success:
                success_list.append(word)
    return success_list
