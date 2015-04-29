__author__ = 'jeffmarkey'

def detect_anagrams(word, list):
    match_val =  ''.join(sorted(word.lower()))
    return_list = []
    for elm in list:

        if (len(word) == len(elm)):
            test_val = ''.join(sorted(elm.lower()))
            if ((test_val == match_val) and (elm.lower() != word.lower())):
                return_list.append(elm)

    return return_list
