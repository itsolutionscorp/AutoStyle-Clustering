def combine_anagrams(words)
  anagrams = {}
  words.each do |word|
    if anagrams[word.downcase.chars.sort.join]
      anagrams[word.downcase.chars.sort.join] = anagrams[word.downcase.chars.sort.join] + [word]
    else
      anagrams[word.downcase.chars.sort.join] = [word]
    end
  end
  return anagrams.values
end
