def fill_counting_list(word_list):
    counting_list = dict()
    for word in word_list:
        if word == "":
            continue
        if word not in counting_list:
            counting_list[word] = 0
        counting_list[word] += 1
    return counting_list

def replace_line_breaks(sentence):
    return sentence.replace("\n"," ")

def get_word_list(sentence):
    return sentence.split(" ")

def word_count(sentence):
    sentence = replace_line_breaks(sentence)
    return fill_counting_list(get_word_list(sentence))
