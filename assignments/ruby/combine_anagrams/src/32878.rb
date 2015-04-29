def combine_anagrams(words)
  anagrams = Hash.new
  words.each do |word|
    ana = word.downcase.chars.sort.join
    anagrams[ana] = Array.new if (not anagrams.has_key?(ana))
    anagrams[ana].push(word)
  end
  return anagrams.values
end