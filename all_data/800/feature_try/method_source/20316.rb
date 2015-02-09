def combine_anagrams(words)
  result = {}
  words.each do |word|
    result[word.downcase.chars.sort.join] ||= []
    (result[word.downcase.chars.sort.join] << word)
  end
  result.values
end