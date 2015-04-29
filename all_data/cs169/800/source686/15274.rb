def combine_anagrams(list)
  
  h = Hash.new()
  list.each { |word| key = word.downcase.split('').sort.join('')  
      if h.has_key?(key) 
        h[key] << word
      else
        h[key] = [word]
      end     
  } 
 h.values

end



#list = ['cars', 'for', 'potatoes', 'racs', 'four','Scar', 'creams', 'scream']
#list = []
#combine_anagrams(list)

