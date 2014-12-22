###########################################################################
# anagrams.rb
#
# combine_anagrams(words): Groups an array of words into anagram groups.
#
# Author:: Ryan Beckett
# Date Created:: 5/30/2012
#
###########################################################################

def combine_anagrams(words)
     ana = []
     words.each { |w|
          grp = []
          words.each { |t|
               if(w.downcase.chars.sort.join == t.downcase.chars.sort.join)
                    grp.insert(grp.length, t)
               end
          }
          ana.insert(ana.length, grp)
     }
     ana.uniq
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])