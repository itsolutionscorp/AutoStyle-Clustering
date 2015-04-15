def combine_anagrams(words)
  h = Hash.new
  words.each do |w|
    w_sorted = w.downcase.chars.sort.join
    h.has_key?(w_sorted) ? ((h[w_sorted] << w)) : (h[w_sorted] = [w])
  end
  array = []
  h.each_key { |key| (array << h[key]) }
  array
end

