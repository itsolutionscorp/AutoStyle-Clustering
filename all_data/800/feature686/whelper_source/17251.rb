def combine_anagrams(words)
  h = {}
  words.each do |w|
    key = w.downcase.chars.sort { |a, b| a.casecmp(b) }.join
    h.has_key?(key) ? ((h[key] << w)) : (h[key] = [w])
  end
  a = []
  h.each { |key, val| (a << val) }
  a
end

