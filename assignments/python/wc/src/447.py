def word_count(phrase):
    phrase = ''.join(i for i in phrase if (48<=ord(i)<=57) or (65<=ord(i)<=90) or (97<=ord(i)<=122) or i == ' ').lower().split()    
    return {word:phrase.count(word) for word in phrase}
