'''
 counts frequency of chars in a string separated by whitespace
'''
import re
__author__ = 'ashaver'
prog = re.compile('\s*')
def word_count(str):
    words = prog.split(str)
    wordDict = {}
    for word in words:
        if not wordDict.has_key(word):
            wordDict[word] = words.count(word)
    return wordDict
