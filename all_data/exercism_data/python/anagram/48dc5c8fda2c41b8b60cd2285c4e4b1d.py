# turns a word in to a word tuple of (char, char count)
# not case-sensitive
def word_tuple(word):
    w_tuple = {}
    for x in word.upper():
        if x not in w_tuple:
            w_tuple[x] = 1
        else:
            w_tuple[x] += 1
    return w_tuple


# helper function returns wether one word is an anagram of another
def is_anagram(ana, word):
    # check length and equality first
    if len(word) <> len(ana) or word.upper() == ana.upper():
        return False
    else:
        #turn each word in to a tuple with (char , char count) and compare
        if  word_tuple(ana) == word_tuple(word):
            return True
        else:
            return False

# returns any anagrams of main_word in word_list
def detect_anagrams(main_word, word_list):
    output = []
    for word in word_list:
        if is_anagram(main_word, word):
            output.append(word)
    return output
    
