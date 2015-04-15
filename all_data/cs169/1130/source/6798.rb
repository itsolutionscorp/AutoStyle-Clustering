def combine_anagrams(words)
  result = Array.new
  found = false;
  words.each {
    |word|
    found = false
    result.each {
      |anagramList|
      if (anagramList[0].downcase.chars.sort.join == word.downcase.chars.sort.join)
        anagramList.push(word)
        found = true
        break
      end
    }
    if (!found)
      result.push(Array.new(1, word))
    end
  }
  return result
end

#p combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
