def combine_anagrams(words)
  hash = {}
  words.each do |word|
    if hash.has_key?(word.chars.sort(&:casecmp).join) then
      hash[word.chars.sort(&:casecmp).join] = (hash[word.chars.sort(&:casecmp).join] << word)
    else
      hash[word.chars.sort(&:casecmp).join] = [word]
    end
  end
  hash
end

