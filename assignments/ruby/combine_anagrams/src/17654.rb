def combine_anagrams(words)
  combos = Hash.new([])
  words.each do |word|
    key = word.downcase.split("").sort.join
    combos[key] = [] if (not combos.has_key?(key))
    (combos[key] << word)
    puts(key)
  end
  combos.values
end