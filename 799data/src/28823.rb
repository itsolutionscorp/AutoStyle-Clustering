def combine_anagrams(words)
  newWords = Hash.new
  words.each{|word|
    currentword = word.downcase.chars.sort.join
    if (!newWords.has_key?(currentword))
      newWords[currentword] = Array.new
    end
    newWords[currentword][newWords[currentword].length] = word
  }
  newWords.values 
 end

# words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream','UFOr','TOTASOEP']
# combine_anagrams(words)
