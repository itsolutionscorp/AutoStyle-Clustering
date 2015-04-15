def combine_anagrams(words)
  anagrams = Hash.new()
  words.each do |word|
    if anagrams.has_key?(word.downcase.chars.sort.join)
      anagrams[word.downcase.chars.sort.join].push(word)
    else
      anagrams[word.downcase.chars.sort.join] = [word]
    end
  end
  return anagrams.values()
end

print combine_anagrams(["hello", "HeLLo"])
