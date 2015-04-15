#function to isolate words and count the number of times they appear in a phrase
def word_count(phrase):
    #words are the keys and the word count are the values
    word_count_dict={}
    #splits the words in the phrase according to the spaces
    word_split=phrase.split()
    #for loop to go through each word in word_split
    for word in word_split:
        #if the word is not already in the dictionary, add one to the counter
        if word not in word_count_dict:
            word_count_dict[word]=1
        #if the word is already in the dictionary, add 1 to the counter, thus increasing
        #the count by 1
        else:
            word_count_dict[word]=word_count_dict[word]+1
    return word_count_dict
