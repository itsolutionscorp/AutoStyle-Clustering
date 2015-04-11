def word_count(string):
    result_dict = {}
    formatted_string = string.replace('\n', ' ')
    tokenized_string = formatted_string.split()
    for token in tokenized_string:
        if not token in result_dict.keys():
            result_dict[token] = 1
        else:
            result_dict[token] += 1
    return result_dict
