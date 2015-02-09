def combine_anagrams(words)
  hash = {}
  result = []

  words.each { |word|
    if hash.has_key?(word.downcase.chars.sort.join)
      hash[word.downcase.chars.sort.join] << word
    else
      hash[word.downcase.chars.sort.join] = [ word ]
    end
  }

  hash.each { |key, value| result << value }
  result
end
