def combine_anagrams(words)
   retval = []
   angs = []
   words.each { |word|   
      str = word.downcase.split('').sort.join
      ind = angs.index(str)
      if ind.nil?
        retval.push([word])
        angs.push(str)    
      else
        retval[ind].push(word)
      end   
   }
   return retval
end
