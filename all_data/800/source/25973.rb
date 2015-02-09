def combine_anagrams(words)
  results = Hash.new {|hash,key| hash[key] = Array.new}
  words.each {|word|     
    newWord = word.downcase.chars.sort.join 
    results[newWord].push(word)
    }
  return results.values
end
sampleWords = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(sampleWords).inspect