# Homework 1 - Part 3

def combine_anagrams(words)
  
  results = []
  
  words.each do |word|
    match = false
    results.each do |resultset|
      if ( resultset.length > 0 && resultset[0].downcase.chars.sort.join('') == word.downcase.chars.sort.join('') )
        match = true
        resultset.push(word)
      end
    end
    if match == false
      resultset = [].push word
      results.push resultset
    end
  end
  
  return results
  
end
