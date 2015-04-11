def word_count(string):
    result_dict = {}
    tokenized_string = string.split()
    for token in tokenized_string:
        if token not in result_dict:
            result_dict[token] = 1
        else:
            result_dict[token] += 1
    return result_dict
