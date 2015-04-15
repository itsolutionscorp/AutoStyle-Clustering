def anagrams?(w1,w2)
   return w1.downcase.split('').sort == w2.downcase.split('').sort
end
def combine_anagrams(words)
   h = Hash.new(0)
   words.each do |w1|
      key = w1.downcase.chars.sort.join 
      if h.has_key?(key) == false
         temp_array = []
         words.each do |w2|
            if anagrams?(w1, w2)
               temp_array.push(w2)
            end
         end
         if h.has_key?(key) == false
            h[key] = temp_array
         end
      end   
   end
   result_array = []
   h.each {|key, value|
      result_array << value} 
   puts result_array
   return result_array
end
#combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
