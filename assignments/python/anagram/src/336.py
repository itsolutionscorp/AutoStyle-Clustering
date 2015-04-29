def detect_anagrams(word,s):
    # Create a list of ordinal integers for the case-insensitive
    # lower case representation of our word
    nums = [ord(letter) for letter in word.lower()]
    
    # Sort the list from smallest to largest
    nums.sort()
    
    # Store our answers in a list
    ans = []
    
    # Now, for each word given
    for item in s:
        
        # Create the list of ordinal integers for the case-insensitive
        # lower case representation of our word, and sort them
        temp = [ord(letter) for letter in item.lower()]
        temp.sort()
        
        # If they match our given word and aren't the same word,
        # add it to our answer
        if temp == nums and item.lower() != word.lower(): ans.append(item)
        
    return ans
