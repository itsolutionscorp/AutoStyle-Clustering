def combine_anagrams(words)
  result = {}
  words.each do |word|
    key = word.downcase.chars.sort.join
    result[key] = [] if result[key] == nil
    result[key] << word
  end
  result.values
end
