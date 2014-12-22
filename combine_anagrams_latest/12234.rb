def combine_anagrams(words)
  results = Hash.new([])
  words.each do |w|
    ana = w.downcase.chars.sort.join
    results[ana] = results[ana] + [w]
  end
  results.values
end
