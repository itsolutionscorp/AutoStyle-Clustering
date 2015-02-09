thewords = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

def combine_anagrams(words)
  results = Hash.new(Array.new)
  
  words.each {|word|     
    worddown = word.downcase.chars.sort.join 
    puts worddown
    results[worddown] = results[worddown].push(word)
    }
  
  return results.values
end

retval = combine_anagrams(thewords)

retval.each {|it| puts it.to_s + "|"}