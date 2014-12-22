def combine_anagrams(words)
  h = {}
  words.each do |word|
    key = word.downcase.scan(/./).sort.join
    h[key] = [] unless h.has_key?(key)
    (h[key] << word)
  end
  h.values
end