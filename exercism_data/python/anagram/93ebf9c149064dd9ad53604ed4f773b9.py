'''
Created on Oct 24, 2014

@author: jbarni00
'''

def detect_anagrams(theword, wordlist):
    anagramlist = []

    for a_word in wordlist:
        if (''.join(sorted(theword.lower())) 
             == ''.join(sorted(a_word.lower()))
             and theword.lower() != a_word.lower()):
            anagramlist.append(a_word)
                   
    return anagramlist
