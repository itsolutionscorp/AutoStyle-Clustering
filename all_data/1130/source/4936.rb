def combine_anagrams (words)
a=words
b=Hash.new
a.each{|e| k = e.downcase.split(//).sort.to_s; b[k]=Array.new }
a.each{|e| k = e.downcase.split(//).sort.to_s; b[k] << e }
c=Array.new
b.each_key{|e| c <<  b[e]}
c
end

