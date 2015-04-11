import re
import collections

def word_count(text):
    counter = WordCounter()
    return counter.count(text)
    
class WordCounter(object):
    '''
    Counts how many times each word in a given phrase occurs
    '''
    #initialize a regular expression that will extract each word in a given phrase
    p = re.compile('\S+')
        
    def count(self, text):
        matches = self.doMatch(text)
        counter = self.createCounter(matches)
        return self.formatDictionary(counter)
    
    def doMatch(self, text):
        '''
        Evalueates the phrase against the regular expression and extracts all matches
        '''
        return self.p.findall(text)
        
    def createCounter(self, matches):
        '''
        creates a counter object from the collections module
        '''
        return collections.Counter(matches)
        
    def formatDictionary(self, counter):
        '''
        creates a normal dictionary from the counter class
        '''
        return dict(counter)
    
