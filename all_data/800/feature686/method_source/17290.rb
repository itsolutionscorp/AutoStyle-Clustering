def combine_anagrams(words)
  anagrams = Hash.new([])
  words.each do |word|
    if anagrams.has_key?(word.downcase.split("").sort.join) then
      (anagrams[word.downcase.split("").sort.join] << word)
    else
      anagrams[word.downcase.split("").sort.join] = [word]
    end
  end
  anagrams.values
end