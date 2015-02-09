def combine_anagrams(words)
  h = {}
  words.each do |x|
    k = x.downcase.split(//).sort.join
    h.has_key?(k) ? ((h[k] << x)) : (h[k] = [x])
  end
  res = []
  h.each_key { |key| (res << h[key]) }
  res
end