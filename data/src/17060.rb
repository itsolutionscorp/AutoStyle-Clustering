def combine_anagrams(words)
  anagrams = []
  return anagrams if (words.length == 0)
  until words.empty? do
    (word = words.first
    anagrams.push(words.select do |match|
      word.downcase.chars.sort.join.eql?(match.downcase.chars.sort.join)
    end)
    words.delete_if do |match|
      word.downcase.chars.sort.join.eql?(match.downcase.chars.sort.join)
    end)
  end
  return anagrams
end