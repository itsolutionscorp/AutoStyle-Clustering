def combine_anagrams(words)
  anagrams = {}
  words.each do |w|
    x = w.downcase.chars.sort.join
    anagrams.has_key?(x) ? ((anagrams[x] << w)) : (anagrams[x] = [w])
  end
  anagrams.values
end