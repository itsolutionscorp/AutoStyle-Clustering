#!/usr/bin/ruby

require 'pp'

tst = ['cars', 'for', 'potatoes', 'racs', 
       'four', 'scar', 'creams', 'scream'] 

def combine_anagrams(words)
   rtn = []
   words.each { |w|
      added = false
      rtn.each { |a|
         if(w.downcase.chars.sort.join ==
            a[0].downcase.chars.sort.join)
            a << w
            added = true
         end
      }
      if !added 
        rtn << [w]
      end
   }
   return rtn               
end

pp combine_anagrams(tst)

