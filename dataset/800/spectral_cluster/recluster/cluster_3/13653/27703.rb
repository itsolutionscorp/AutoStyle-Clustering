def combine_anagrams(words)
  #stuff
  anagrams = Hash.new
  words.each { |word| temp = word.downcase.chars.sort.join; anagrams[temp]?anagrams[temp]+=1:anagrams[temp]=1}
  results = Array.new(anagrams.length) {Array.new}
  words.each { |word| i=anagrams.keys.index(word.downcase.chars.sort.join); results[i].push(word)}
  return results
end

puts combine_anagrams (['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
