#!/usr/bin/env ruby
def combine_anagrams(words)
   out = []
   while words.length>0
     newwords = []
     anagram = [words[0]]
     test = words[0].downcase.split(//).sort.join('')
       (1...(words.length)).each {|i| 
         if test == words[i].downcase.split(//).sort.join('')
          # if not anagram.include? words[i]
             anagram << words[i]
          # end
         else
           newwords << words[i]
         end
       }
     words = newwords
     out << anagram
   end
   return out
end


#input = ['cars','for','potatoes','racs','four','scar','creams','scream','scar','SCAR']
#input = ['a','a','A','ciao','CIAO']
#input = ['pots', 'spot', 'SPOT','ciao','stop', 'tops', 'tops']
#puts  combine_anagrams(input);  
