def ac(w)
  w.downcase.split('').sort.inject(''){|m, c| m = m+c}
end

def combine_anagrams(words)
 h = {}
 words.map{|w| 
   k = ac(w)
   if h[k] == nil then h[k] = [] end
   h[k].push(w)
 }
 res = []
 h.each_key{|k| res.push(h[k]); puts "pushed"; puts h[k] }
 res
end

 