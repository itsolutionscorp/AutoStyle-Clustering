''' Anagram Test in Python'''

from collections import Counter

def detect_anagrams(base_word, word_list):

  ''' 
      break down the words on the list into dictionary 
      containing counts of letters and compare it with
      the base_word.
  '''
  anagrams = []
  letter_breakdown = []
  base_word_breakdown = Counter(base_word.lower())
  
  letter_breakdown = [ Counter(words.lower()) 
                       for words in word_list 
                     ]

  anagrams = [ words 
               for (words,letters) in zip(word_list, letter_breakdown)
               if (base_word != words.lower()) and (base_word_breakdown == letters) 
             ]
   
  return anagrams

