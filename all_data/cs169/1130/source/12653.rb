def combine_anagrams(words)
  au = words.map {|e| e.split("").sort.join}.uniq
  t = Array.new
  au.each do |u|
    rs = Array.new
    words.each do |e| 
      if (e.split("").sort.join == u) 
        rs << e
      end
    end
    t << rs
  end
  return t
end