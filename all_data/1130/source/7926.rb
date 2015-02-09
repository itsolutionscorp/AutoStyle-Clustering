#Part 3
#Given an array of strings, write a method that groups them into anagram groups 
#and returns the array of groups. Case doesn't matter in classifying string as 
#anagrams (but case should be preserved in the output), and the order of the anagrams 
#in the groups doesn't matter.

def combine_anagrams(words)
  anagram_group = Hash.new([])
  words.each {|word| anagram_group[word.downcase.split(//).sort.join] += [word]}
  return anagram_group.values
end

#p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
