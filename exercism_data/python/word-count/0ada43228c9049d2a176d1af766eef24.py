import string

def string_to_list_and_clean(input):
    exclude = set(string.punctuation)
    clean_input = ''.join(ch.lower() for ch in input if ch not in exclude) 
    return clean_input.split()

def sorted_unique_words(input):
    return sorted(list(set(input)))

def occurences(word, lst):
    return lst.count(word)

def word_count(input):
    clean_list = string_to_list_and_clean(input)
    unique_words = sorted_unique_words(clean_list)
    data = {}
    for word in unique_words:
        data[word] = occurences(word, clean_list)
    return data
