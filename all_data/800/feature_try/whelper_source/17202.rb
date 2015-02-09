def combine_anagrams(words)
  anagrams = {}
  words.each do |e|
    key = e.downcase.chars.sort.join
    anagrams[key] = anagrams[key].nil? ? ([e]) : ((anagrams[key] << e))
  end
  anagrams.values
end

