#wordcount.py
#for counting words!

#word_count
#input: string sentence
#output: dictionary word_dic
def word_count(sentence):
    word_list = sentence.split()
    word_dic = {}
    for word in word_list:
        word_dic[str(word)] = word_list.count(word)
    return word_dic
