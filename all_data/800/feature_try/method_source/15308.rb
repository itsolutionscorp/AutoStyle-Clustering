def combine_anagrams(words)
  return words.classify { |x| x.downcase.chars.sort.join }.each_value.collect do |x|
    x.to_a
  end
end