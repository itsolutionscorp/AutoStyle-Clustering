#!/usr/bin/ruby


def combine_anagrams(words)
  
  h = Hash.new(0)
  
  words.each do |w| 
    
    sortedhash = w.downcase.chars.sort.join
    
    list = h[sortedhash]
    
    
    if list == 0
      h.store(sortedhash, [w])
    else
      list.push(w)    
    end
        
  end
  
  result = []
  # convert hash into array
  h.each do |k, v|
    result.push v
  end
  
  return result
  
end


