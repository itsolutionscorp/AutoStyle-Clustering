#Author: Varga Levente, vlzoltan@gmail.com
#License:
#Date: June 3rd 2012

# Implementation of anagrams tester HW 1.3


# @param [Object] words
def combine_anagrams(words)
  anagrams = []
  available_words = words
  words.each do |e|
    group = []
    temp_words = []
    anagram_invariant = e.downcase.chars.sort.join
    available_words.each do |i|
      test = i.downcase.chars.sort.join
      if test == anagram_invariant
        group.push(i)
      else
        temp_words.push(i)
      end
    end
    if(!group.empty?)
      anagrams.push(group)
    end
    available_words = temp_words
  end
  return anagrams
end

#For testing purposes
#puts combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s


