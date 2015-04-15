def combine_anagrams(words)
  result = words.inject({}) do |result, word|
    result[word.downcase.chars.sort.join] ||= []
    result[word.downcase.chars.sort.join] << word
    result
  end

  result.values
end
