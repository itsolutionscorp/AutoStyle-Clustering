
def combine_anagrams(words)
  # Use a hash to group the anagrams.  Set up a default block for when an undefined key
  #  is used to create a blank array for that key.
  groups = Hash.new { |hash, key| hash[key] = Array.new} 
                               
  #loop through each word                               
  words.each do |word|
     # split the word by letters, sort the letters, use that as a hash key, then add
     #   the base word to the hash.
     groups[word.downcase.split(/|/).sort].push(word)        
  end
 
  #return just the values, not the hash keys.
  return groups.values
end

