def combine_anagrams(words)
  word_map = Hash.new
  words.each do |word|
    sorted_downcase = word.downcase.chars.sort.join
    if word_map.has_key?(sorted_downcase) then
      word_map[sorted_downcase].concat([word])
    else
      word_map[sorted_downcase] = [word]
    end
  end
  return word_map.values
end