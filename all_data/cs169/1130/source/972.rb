# An anagram is a word obtained by rearranging the letters of another word. For example,
# "rats", "tars" and "star" are an anagram group because they are made up of the same letters.

# Given an array of strings, write a method that groups them into anagram groups and returns
# the array of groups. Case doesn't matter in classifying string as anagrams (but case should be
# preserved in the output), and the order of the anagrams in the groups doesn't matter.

# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)

  hash = Hash.new

  words.each do |str|

     sorted = str.upcase.chars.sort.join

     if (hash[sorted]== nil)
       hash[sorted] = Array.new
     end

     hash[sorted][hash[sorted].length] = str
  end

  return hash.values
end

combine_anagrams(['a', 'A'])
# combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]

