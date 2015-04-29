def combine_anagrams(words)
  h = {}
  words.each do |w|
    puts(w)
    d = w.downcase.split(//).sort.to_s
    h.has_key?(d) ? ((h[d] << w)) : (h[d] = [w])
  end
  return h.values
end