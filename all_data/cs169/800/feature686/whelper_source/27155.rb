def combine_anagrams(words)
  dict = Hash.new([])
  words.each do |word|
    sorted_word = word.upcase.split("").sort.join
    dict[sorted_word] = [] unless dict.include?(sorted_word)
    (dict[sorted_word] << word)
  end
  return dict.values
end

