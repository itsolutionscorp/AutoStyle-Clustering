
def combine_anagrams(words)
 results_array = Array.new
 words.each{|i| 
        loop_array = Array.new

   	words.each{ |j| 
         if i.chars.sort.join == j.chars.sort.join and not results_array.flatten.include?(j)
           loop_array.push(j)
         end
        
	}
         
        if not loop_array.empty?
 	  results_array.push(loop_array)
        end
 }

 return results_array
end


print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
