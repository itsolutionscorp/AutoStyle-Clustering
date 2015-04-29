def combine_anagrams (words)
  h = {}
  words.each do |w|
    puts w
    d = w.downcase.split(//).sort.to_s #.downcase
    if h.has_key?(d)
      h[d] << w
    else
      h[d] = [w]
    end
  end
  return h.values
end