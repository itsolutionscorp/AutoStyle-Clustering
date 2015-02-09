def combine_anagrams(words)
  anagramHash = Hash.new(0)
  words.each do |word|
    if anagramHash.has_key?(word.downcase.chars.sort { |a, b| a.casecmp(b) }.join) then
      a = anagramHash[word.downcase.chars.sort { |a, b| a.casecmp(b) }.join]
      (a << word)
      anagramHash[word.downcase.chars.sort { |a, b| a.casecmp(b) }.join] = a
    else
      anagramHash[word.downcase.chars.sort { |a, b| a.casecmp(b) }.join] = [word]
    end
  end
  return anagramHash.values
end