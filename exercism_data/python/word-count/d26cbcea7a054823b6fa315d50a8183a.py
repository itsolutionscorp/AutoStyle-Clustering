def word_count(string):
    result_dict = {}
    tokenized_string = string.split()
    for token in tokenized_string:
        if token not in result_dict:
            result_dict[token] = tokenized_string.count(token)
    return result_dict
