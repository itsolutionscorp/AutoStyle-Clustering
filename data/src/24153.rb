def combine_anagrams(words)
  wordsHash = Hash.new { |h, k| h[k] = [] }
  i = 0
  words.each  { |word| wordsHash[word.downcase.chars.sort.join] = wordsHash[word.downcase.chars.sort.join].push(word)
  
		}  
  
  returnArray = []
  wordsHash.keys.each {|k| returnArray <<wordsHash[k] }
  returnArray
end

input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(input)
