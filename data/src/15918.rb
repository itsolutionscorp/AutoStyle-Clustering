def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    lower_case_word = word.downcase
    sorted_string = lower_case_word.each_char.sort.join
    if anagrams.has_key?(sorted_string) then
      (anagrams[sorted_string] << word) unless anagrams.has_value?(word)
    else
      word_array = Array.new
      (word_array << word)
      anagrams[sorted_string] = word_array
    end
  end
  anagrams_array = Array.new
  anagrams.each { |key, value| (anagrams_array << value) }
  return anagrams_array
end