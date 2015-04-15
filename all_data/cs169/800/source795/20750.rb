def combine_anagrams(words)
  anagrams = Hash.new
  anagrams_array = Array.new
  words.each do |word|
    sorted_word = word.chars.sort { |a, b| a.casecmp(b) }.join.downcase
    if anagrams.has_key?(sorted_word)
      anagrams.store(sorted_word, anagrams[sorted_word] + ',' + word)
    else
      anagrams.store(sorted_word, word)
    end
  end
  anagrams.each do |key, value|
     anagrams_array << value.split(',')
  end
  return anagrams_array
end
