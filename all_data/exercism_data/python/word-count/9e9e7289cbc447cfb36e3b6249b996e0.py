#
# Python script to count words
#

def word_count(sentence):
    # remove extra whitespace before split
    temp1 = sentence.replace('  ','')
    # remove tabs and new lines
    temp2 = temp1.replace('\n',' ')
    # split string into array of strings
    words = temp2.split(' ')
    word_dict = {} # declare dictionary
    for i in words:
        count = words.count(i) 
        word_dict[i] = count
    return word_dict
