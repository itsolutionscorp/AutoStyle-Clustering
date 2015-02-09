def combine_anagrams(words)
  h = Hash.new(0)
  words.each do |w|
    key = w.downcase.chars.sort.join
    if h[key].class != Array
      h[key] = []
    end
    h[key] << w
  end
  ret = []
  h.each do |k, v|
    ret << v
  end
  return ret
end