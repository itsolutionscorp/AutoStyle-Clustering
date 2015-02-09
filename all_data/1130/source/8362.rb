def combine_anagrams(words)
  result = []
  words.each { |word|
    found =false
    result.each{ |group|
      if (group[0].downcase().chars.sort.join == word.downcase().chars.sort.join)
         group << word
         found = true
      end
    }  
    if !found
      result << [ word ]
    end
  }
  return result
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] )
