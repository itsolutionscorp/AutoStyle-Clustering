#!/usr/bin/python
# exercism python # 9: anagram

def detect_anagrams(word, candidate_list):
    return_list = []
    word_letters = [letter for letter in word.lower()]
    word_letters.sort()
    for candidate in candidate_list:
        candidate_letters = [letter for letter in candidate.lower()]
        candidate_letters.sort()
        if word_letters == candidate_letters and word.lower() != candidate.lower():
            return_list.append(candidate)
    return return_list
            
        
            
