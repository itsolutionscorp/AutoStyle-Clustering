def combine_anagrams(words)
  groups = Hash.new
  words.each do |word|
    anagram_form = word.downcase.chars.sort.join
    (groups[anagram_form] ||= []) << word 
  end
  
  groups.values
end

## input: 
#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
##  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
#print combine_anagrams(words)

  