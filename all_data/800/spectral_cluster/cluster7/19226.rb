def combine_anagrams(words)
  solution =Array.new
  if (words.empty?)
  solution=[]
   elsif (words.length==1)
  solution=[words]
 else

     modify=words.map{|b| b.downcase.chars.to_a.sort.join}
   for i in 0..modify.length-1
     tempsol=[words[i]]
    for j in 0..modify.length-1
        if (modify[i].eql?(modify[j] && i!=j))
           tempsol << words[j]
        end
     end
    solution << tempsol
   end 
   solution.uniq!
   end  
return solution
end

 

@answer=combine_anagrams(['for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'cars'])
#answer.each{|a| puts a.inspect}
answer=combine_anagrams(['Cat', 'poo', 'roo', 'foo', 'soo', 'car'])
#answer=combine_anagrams(['f'])
answer.each{|a| puts a.inspect}