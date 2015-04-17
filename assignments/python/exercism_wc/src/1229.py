def word_count(words):
     wordtuple = ( words.split() )   ###Break up into individual words
     dictionary = {}
     for item in wordtuple:          ###Loop over them.
          if not item in dictionary: ###If not already here,
               dictionary[item] = 1
          else:
               dictionary[item] += 1 ###If it is here, increment.
     return dictionary      
