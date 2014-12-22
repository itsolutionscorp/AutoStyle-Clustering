
def combine_anagrams(words)
  results = Hash.new {|h,k| h[k] = Array.new}
  
  words.each {|word|     
    worddown = word.downcase.chars.sort.join     
    results[worddown] = results[worddown].push(word)
    }
  
  return results.values
end
