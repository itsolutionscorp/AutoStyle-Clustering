def combine_anagrams(words)
  hash = Hash.new;
  
  words.each {
    |w| 
    
    i = w.downcase.split(//).sort.to_s

    if (!hash[i]) then
      hash[i] = Array.new
    end

    hash[i].push w
  }
  
  return hash.values
end

# puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']).to_s
