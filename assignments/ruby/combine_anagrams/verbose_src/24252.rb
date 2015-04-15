def combine_anagrams(words)

   h = Hash.new

   words.each { |w| puts w }

   words.each do |w|
 
   sw = w.downcase.chars.sort.join
        
   (h[sw] ||= []) << w

   end

   return h.values
      
end



#combine_anagrams ['cars', 'stars', 'racs']

#combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']