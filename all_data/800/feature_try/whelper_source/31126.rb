def combine_anagrams(words)
  h = {}
  words.each do |word|
    key = word.downcase.split("").sort.join
    h[key] ? ((h[key] << word)) : (h[key] = [word])
  end
  h.values
end

