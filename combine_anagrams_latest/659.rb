def combine_anagrams(words)
   combine = Array.new
   
   ha = Hash.new
   
   for i in words
     j = i.downcase
     j = j.split("")
     j = j.sort
     j= j.join
     
     if ha[j]
       ha[j].push(i)
     else
       ha[j] = Array.new
       ha[j].push(i)  
     end
     
    
   end
   
   ha.each_value do |k|
     combine.push(k)
   end
   puts combine
   return combine
   
   
end

