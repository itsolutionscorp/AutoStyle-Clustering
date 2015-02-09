def combine_anagrams(words)
  hash = {}
  result = []
  words.each do |word|
    if hash.has_key?(word.downcase.chars.sort.join) then
      (hash[word.downcase.chars.sort.join] << word)
    else
      hash[word.downcase.chars.sort.join] = [word]
    end
  end
  hash.each { |key, value| (result << value) }
  result
end