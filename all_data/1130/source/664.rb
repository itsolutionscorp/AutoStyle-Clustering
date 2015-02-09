def combine_anagrams(words) 
  h = Hash.new
  words.each {|w| s = w.downcase.split(//).sort;  h[s] = h[s].to_a << w}
  h.values
end