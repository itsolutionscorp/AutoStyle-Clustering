def combine_anagrams(words)
  anagrams = Array.new
  sorted_words_array = Array.new
  words.each do |word|
    anagrams_word = word.downcase.chars.sort(&:casecmp).join
    if sorted_words_array.include?(anagrams_word) then
      anagrams[sorted_words_array.index(anagrams_word)].insert(-1, word)
    else
      sorted_words_array.insert(-1, anagrams_word)
      anagrams.insert(-1, Array[word])
    end
  end
  anagrams
end