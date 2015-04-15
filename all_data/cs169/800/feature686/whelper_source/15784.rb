def combine_anagrams(words)
  anagrams = {}
  words.each do |w|
    key = w.downcase.scan(/[a-zA-Z]/).sort.join
    anagrams.has_key?(key) ? ((anagrams[key] << w)) : (anagrams[key] = [w])
  end
  anagrams.values
end

