#1. First split the words into an array
from __builtin__ import True

def word_count(word_to_count):
    word_to_count.rstrip(word_to_count)
    word_to_count.lstrip(word_to_count)
    word_to_count = word_to_count.replace("\n", " ")
    words = word_to_count.split(' ')
    
    words = remove_none(words)
    #print len(words)
    
    final_words = []
    counter = 0
    for word in words:
        if found_inside(word, final_words) == False:
            final_words.append( word )
            counter += 1
    #print "Counter: "+str(counter)+" FinalWords Len: "+str(len(final_words))
    
    #Now get the no of each
    #Create a dictionary for that
    word_dic = {}
    
    for word in final_words:
        counter = 0
        for word_item in words:
            if word_item == word:
                counter += 1
        
        word_dic.update({word : counter})
    
    
    return word_dic



def found_inside(word, word_tuple):
    for word_item in word_tuple:
        if word == word_item:
            return True
    
    return False

def remove_none(word_list):
    new_word_list = []
    
    for word in word_list:
        if word == "":
            continue
        else:
            new_word_list.append(word)
    
    return new_word_list
