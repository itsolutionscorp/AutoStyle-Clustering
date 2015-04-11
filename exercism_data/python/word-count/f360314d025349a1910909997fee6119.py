import re

def word_count(sentence):

    # Create an empty dictionary {}, and an empty list []
    wordCounts = {}
    wordList = []
    
    # Replace newline characters with a space.
    # Split the sentence into a list of words.
    wordList = re.split(" ", sentence.replace("\n"," "))

    # Iterate over each word in the list.
    for word in wordList:
        # When a word is not an empty string...
        if word != "":
            # Add that word to the "wordCounts" dictionary as a key.
            # Set the key's value to the sum of all occurences of the word in "wordList."
            wordCounts[word] = wordList.count(word)

    # Return the "wordCounts" dictionary.
    return wordCounts




