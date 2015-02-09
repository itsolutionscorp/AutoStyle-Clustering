def combine_anagrams(words)
  z=words.sort_by{ |elem| elem.size }
  arr=Array.new(0)
  arr2=Array.new(0)
  0.upto(z.size-1) {
    |i|
       0.upto(z.size-1) {
         |j|
         if i!=j
              if z[i].unpack("c*").sort.pack("c*").to_s.downcase.sum == z[j].unpack("c*").sort.pack("c*").to_s.downcase.sum
              arr.push(z[i])
              else  
              arr2.push(z[i])
              end
         end
    }
    }
   p [arr.uniq.sort_by{ |elem| elem.size }, arr2.uniq.sort_by{ |elem| elem.size } - arr.uniq.sort_by{ |elem| elem.size }]
end
 
combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
