def combine_anagrams(words)
  return if (words == nil)
  h = Hash.new(Array.new(1, ""))
  words.each do |word|
    next if (word == nil)
    key = word.downcase.split(//).sort.join
    h.has_key?(key) ? (h[key] = (h.fetch(key) << word)) : (h[key] = [word])
  end
  return h.values
end