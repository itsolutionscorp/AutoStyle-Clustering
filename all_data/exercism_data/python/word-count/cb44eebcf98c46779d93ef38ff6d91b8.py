###
### Word Count Exercise
###

def word_count(words):

     wordtuple = ( words.split() )   ###Break up into individual words
                                     ###on whitespace.
     dictionary = {}

     for item in wordtuple:          ###Loop over them.

          if not item in dictionary: ###If not already here,
                                     ###add it with value of 1.
               dictionary[item] = 1

          else:

               dictionary[item] += 1 ###If it is here, increment.


     return dictionary      

          
