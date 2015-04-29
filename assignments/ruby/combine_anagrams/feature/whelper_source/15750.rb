def combine_anagrams(words)
  h = {}
  words.each do |w|
    a = w.downcase.scan(/./).sort.join
    h[a] = [] if (not h.has_key?(a))
    h[a].push(w)
  end
  return h.values
end

