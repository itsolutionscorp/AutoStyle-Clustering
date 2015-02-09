def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    w = word.downcase.chars.sort.join
    if anagrams.has_key?(w) then
      anagrams[w] = anagrams[w] << word
    else
      anagrams.merge!(w => [word])
    end
  end
  return anagrams.values
end
