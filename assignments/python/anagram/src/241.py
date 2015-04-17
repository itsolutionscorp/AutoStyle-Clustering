__author__ = 'emiller42'
def detect_anagrams(target, word_list):
    # Container for matches
    matches = []
    # Turn target word into a sorted list of lowercase characters
    target_list = sorted(list(target.lower()))
    # iterate through word_list
    # Check to make sure the word isn't the same as the target
    # If not, make it a sorted list of lowercase letters, then
    # compare the the target_list.  If it matches, add the
    # word to the set of matches
    for word in word_list:
        if target.lower() != word.lower():
            temp_word = sorted(list(word.lower()))
            if target_list == temp_word:
                matches.append(word)
    return matches
