def combine_anagrams(words)
  h = Hash.new 
  words.each{|s| 
    key = s.upcase.split(//).sort.join.to_s
    if !h.key?(key)
      h.store(key,[s])
    else
      h.store(key, h.fetch(key)+[s])
    end
  } 
  m = Array.new
  h.each{|k,v| m = m + [v]}
  return m
end