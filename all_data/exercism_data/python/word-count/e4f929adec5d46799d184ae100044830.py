class Sentence():
    
    def __init__(self, sentence):
        
        self.sentence = sentence

    def word_count(self):
        '''Sentence.word_count()

        Counts the number of instances of each word in the sentence.

        Returns dictionary in which the keys are the words from the 
        sentence and the values are number of instances of the words.
        '''
        
        word_list = self.sentence.split()
        unique_word_set = set(word_list)  # a set only allows unique values
        unique_word_list = list(unique_word_set) 

        word_count_dict = {}
        for word in unique_word_list:
            word_count_dict[word] = word_list.count(word)
        return word_count_dict

def word_count( input_sentence ):
    sentence = Sentence(input_sentence)
    
    return sentence.word_count()
            
