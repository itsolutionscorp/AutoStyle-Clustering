import re
def word_count(word):
    word_Hash={}
    words=word.split(" ")  #splits the string 
    for word in words:
        word=re.sub(' ','',word)
        if word in word_Hash:
            word_Hash[word]+=1   
        else:
            word_Hash[word]=1
    print word_Hash
word_count('rah rah ah ah ah\nroma roma ma\nga ga oh la la\nwant your bad romance')
