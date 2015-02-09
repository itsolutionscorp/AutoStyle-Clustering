#!/usr/bin/env ruby
# anagram.rb

def combine_anagrams(words) 
#	<YOUR CODE HERE>

  # initialize array
  anagrams_group = []

  if words.empty? then return anagrams_group end

  anagrams_group = [[words[0]]]
  
  # remove 1st member of words since its already computed
  words.delete_at(0)

  words.each {|word|
    #reset flag
    flag_added = false
    #store word in array of char in alphabet order
    word_array = ((word.downcase).split(//)).sort
    
    # there are 2 case, (1) add to existing group or (2) create a new one
    anagrams_group.each {|group| 
      if (word_array <=> ((group[0].downcase).split(//)).sort)   == 0
        group << word
        flag_added = true
        break
      end    
    }
    if !flag_added 
      anagrams_group << [word]
    end

  }

  return anagrams_group

  # print anagram group
  #print "["
  #(anagrams_group.length).times {|i|
  #  print "["
  #    (anagrams_group[i].length).times {|j|
  #      print "\"#{anagrams_group[i][j]}\""
  #      if j<anagrams_group[i].length-1 then print "," end
  #    }
  #  print "]"
  #  if i<anagrams_group.length-1 then print "," end
  #}
  #puts "]"

end

#test
puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts combine_anagrams([])

