# An anagram is a word obtained by rearranging the letters of another word. For
# example, "rats", "tars" and "star" are an anagram group because they are made up of the same letters.
# Given an array of strings, write a method that groups them into anagram groups and returns the array of groups. 
# Case doesn't matter in classifying string as anagrams (but case should be preserved in the output), and the order of the anagrams in the groups doesn't matter.

w1 = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  #1. detect anagram
  #2. group array
  output = []
  anagrams = []
  words.each do |w|
    sorted =  w.split('').sort.to_s
    anagram_index = anagrams.index(sorted)

    if anagram_index !=nil
      output[anagram_index].push w
    else
      output.push [w]
      anagrams.push sorted 
    end
  end

  output
end

