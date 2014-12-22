def combine_anagrams(words)
  hash = {}
  words.each { |word|
    if hash.has_key?(word.chars.sort(&:casecmp).join)
      hash[word.chars.sort(&:casecmp).join] = hash[word.chars.sort(&:casecmp).join] << word
    else
      hash[word.chars.sort(&:casecmp).join] = [word]
    end
  }

  hash
end

#pp combine_anagrams(['cars', 'for', 'Potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])