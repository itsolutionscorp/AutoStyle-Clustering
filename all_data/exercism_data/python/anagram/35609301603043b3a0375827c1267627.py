'''
Created on Oct 2, 2014

@author: dulshani
'''
def is_anagram(chars, ichar):
    for char in chars:
        if char not in ichar:
            return False
        ichar.remove(char)
    return True


def detect_anagrams(word, word_list):
    anagram_list = []
    chars = list(word.lower())
    for item in word_list:
        ichar = list(item.lower())
        if len(chars) == len(ichar) and chars != ichar and is_anagram(chars, ichar):
                anagram_list.append(item)
    return anagram_list
