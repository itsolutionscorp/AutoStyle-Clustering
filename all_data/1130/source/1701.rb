def combine_anagrams(words)
  h = Hash.new([])
  words.each do |a_word|
    h[a_word.downcase.each_char.sort.join("")] += [a_word]
  end
  return h.values
end