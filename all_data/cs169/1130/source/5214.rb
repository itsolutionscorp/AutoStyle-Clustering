=begin
An anagram is a word obtained by rearranging the letters of another word. For
example, "rats", "tars" and "star" are an anagram group because they are made up of the same
letters.
Given an array of strings, write a method that groups them into anagram groups and returns
the array of groups. Case doesn't matter in classifying string as anagrams (but case should be
preserved in the output), and the order of the anagrams in the groups doesn't matter.
Example:
# input:
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
#
<YOUR CODE HERE>
end
=end

def combine_anagrams(words)
  sorted = Array.new(0)
  grouped = Hash.new(0)
  combined = Array.new(0)
  
  words.each do | word |
    sorted.push word.downcase.chars.sort.join
  end

  sorted.each do | sword |
    grouped[sword.to_s] = Array.new(0)
    words.each do | word |
      if sword == word.downcase.chars.sort.join
        grouped[sword.to_s].push word
      end
    end
  end
  
  grouped.each do | key, group |
    combined.push group
  end
  
  return combined
end

puts combine_anagrams(['cars', 'cars', 'a', 'A', 'a', 'For', 'potatoes', 'rAcs', 'four','scar', 'creams', 'scream']).inspect
