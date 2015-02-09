#Homework1 - Part3 (anagrams)

def combine_anagrams(words)
   puts words
   maphash = Hash.new()
   
   words.each do |w|
      wkey = w.downcase.chars.sort.join
      
      if maphash.key?(wkey)
         maphash[wkey].push(w)
      else
         maphash[wkey] = [w]
      end
   end
   
   return maphash.values
end


