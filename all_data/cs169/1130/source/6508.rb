def combine_anagrams(words)
   h = {}
   words.each{|w| k=w.upcase.scan(/./).sort.join;  h.key?(k) ? h[k] = h[k].push(w) : h[k] = Array[w] }
   a =[] ; i=0
   h.each_value{|v | a[i] =v ; i=i + 1 }
   a
end


