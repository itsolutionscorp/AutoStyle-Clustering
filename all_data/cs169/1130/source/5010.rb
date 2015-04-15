

# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)

	 hwords = Hash.new()
	 words.each{
    |word|	
    
    #For each word, sort charecters then add it to hash table
    if hwords[word.upcase.chars.sort.join] 
      hwords[word.upcase.chars.sort.join] = hwords[word.upcase.chars.sort.join].push(word)
    else
      hwords[word.upcase.chars.sort.join] = [].push(word)
    end   
	   
	 }
	 
	 
	 hwords.values
  
end


puts combine_anagrams(["race", "care", "bob"])