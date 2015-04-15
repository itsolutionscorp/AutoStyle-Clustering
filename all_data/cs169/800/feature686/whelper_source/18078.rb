def combine_anagrams(words)
  h = Hash.new
  words.each do |word|
    lowercase_word = word.downcase
    sorted_lowercase_word = lowercase_word.chars.sort.join
    similar_words = h[sorted_lowercase_word]
    similar_words = [] if (not similar_words)
    (similar_words << word)
    h[sorted_lowercase_word] = similar_words
  end
  output = []
  h.each_value { |value| (output << value) }
  return output
end

