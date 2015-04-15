def combine_anagrams(words)
  sort_w=Hash.new
  words.each {|w|
  w_sort=w.downcase.split('').sort.join.gsub(/(.)\1{2,}/) { |s| s.length.to_s + s[0,1] }
    if !sort_w.has_key?(w_sort)
      sort_w[w_sort]=[w]
else
  sort_w[w_sort]=sort_w[w_sort].push(w)
    end 
  
} 
out_array=Array.new
sort_w.each_key {|ww| 
  out_array.push(sort_w[ww])
}
#puts sort_w[ww]
return out_array

end
#ord=["cars","sCar","go","Og","fisk","ert","tre","ret","FISK"]
#puts combine_anagrams(ord)
#s = "mississippi" 
 #s.split('').sort.join.gsub(/(.)\1{2,}/) { |s| s.length.to_s + s[0,1] } 
#unpack("c*").sort.pack("c*") 
