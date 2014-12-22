# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  
  h = Hash.new
  
  words.each do |w| 
    key = w.downcase.split(%r{\s*}).sort.join
    
    
    if !h.has_key?(key)
      h[key] = [w]
    else 
      h[key] += [w]
    end
   
  end
  
  h.values
end

#a = combine_anagrams ['cars', 'for', 'potatoes', 'raCs', 'four','scar', 'creaMs', 'ScrEam'] 
#puts a
#a = combine_anagrams ['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#puts a