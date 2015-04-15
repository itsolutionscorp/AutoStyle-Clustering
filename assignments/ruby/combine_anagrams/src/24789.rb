def combine_anagrams(words)
  h = {}
  words.each do |x|
    key = x.downcase.split("").sort.join
    h[key] ? (h[key].push(x)) : (h[key] = [x])
  end
  return h.values
end