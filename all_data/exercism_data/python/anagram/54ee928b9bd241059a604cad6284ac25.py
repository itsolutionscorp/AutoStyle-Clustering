def get_word_dict(word):
    letter_list = [letter for letter in word]
    return {letter.lower(): letter_list.count(letter) for letter in set(letter_list)}
    
def compare_words_dicts(dict1, dict2):
    if len(dict1) != len(dict2):
        return False
    for key, value in dict1.items():
        if value != dict2.get(key):
            return False
    return True
        
def detect_anagrams(word, entry_list):
    entry_tuples = ((entry, get_word_dict(entry)) for entry in entry_list if entry.lower() != word.lower())
    word_dict = get_word_dict(word)
    return [entry for entry, entry_dict in entry_tuples if compare_words_dicts(entry_dict, word_dict)]
