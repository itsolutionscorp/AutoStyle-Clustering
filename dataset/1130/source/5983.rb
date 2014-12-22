def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    key = word.downcase.chars.sort.join
    if anagrams.has_key?(key)
      anagrams[key] << word
    else
      anagrams[key] = Array[ word ]
    end
  end
  output = Array.new
  anagrams.each_value { |value| output << value }
  return output
end
