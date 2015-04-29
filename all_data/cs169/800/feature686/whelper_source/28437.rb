def combine_anagrams(words)
  h = Hash.new
  r = []
  words.each do |i|
    j = i.downcase.chars.sort.join
    h.has_key?(j) ? (h[j] += [i] if (h.has_value?(i) == false)) : (h[j] = [i])
  end
  h.each_value { |value| (r << value) }
  return r
end

