def combine_anagrams(words)
  words.group_by { |word| word.downcase.chars.sort.join }.map do |key, value|
    value
  end
end