def combine_anagrams(words)
  newWords = Hash.new
  words.each do |word|
    currentword = word.downcase.chars.sort.join
    newWords[currentword] = Array.new if (not newWords.has_key?(currentword))
    newWords[currentword][newWords[currentword].length] = word
  end
  newWords.values
end

