def combine_anagrams(words)
  sortWords = Array.new
  anagramHash = Hash.new
  anagramWords = Array.new
  
  words.each do |w|
    t = w.downcase
    #sorting letters for each words and then push the sorted words to sortWords
    sortWords.push t.chars.sort { |a, b| a.casecmp(b) } .join
  end
  
  sortWords.each_index do |index|
    if anagramHash.has_key?(sortWords[index])
      anagramHash[sortWords[index]].push words[index]
    else
      anagramHash[sortWords[index]] = [ words[index] ]  
    end
  end
  
  anagramHash.each_value do |v|
    anagramWords.push v
  end
  
  return anagramWords
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', "A", "a", "HEllo", "hello"])