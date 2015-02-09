def combine_anagrams(words)
  h = Hash.new
  words.uniq.map { |word| h[word] = word.downcase.split(//).sort.join }
  res = Hash.new
  h.each { |key, val| res[val] = h.select { |k, v| (v == val) }.keys }
  res.values
end

