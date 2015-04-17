import sys
import re
def word_count(input):
    word_count_dic  = {}
    split_input = input.split(" ")
    for word in split_input:
        word = word.lower()
        word = re.sub('[^a-zA-Z0-9]','',word)
        word = re.sub('\s','',word)
        if(word):
            word_count_dic[word] = word_count_dic.get(word,0) + 1
    return word_count_dic
if __name__ == '__main__':
    print word_count(sys.argv[1])
