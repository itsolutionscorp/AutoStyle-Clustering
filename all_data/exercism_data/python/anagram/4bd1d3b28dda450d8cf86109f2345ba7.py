'''
anagram.py

Detect anagrams
'''

def detect_anagrams(word, candidates):
    '''
    Given a word and a list of possible anagrams, select the correct sublist

    @param word: the given word
    @param candidates: possible anagrams
    @returns: sublist of actual anagrams
    '''
    sorted_word = ''.join(sorted(word.lower()))
    return [w for w in candidates
            if ''.join(sorted(w.lower())) == sorted_word
                and w.lower() != word.lower()]
