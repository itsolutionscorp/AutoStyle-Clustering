def combine_anagrams(words)
  anaIndex = {}
  words.each{ |w| 
    key = w.downcase.split(//).sort.join
    if anaIndex.has_key?(key)
      anaIndex[key] << w
    else
      anaIndex[key] = [w]
    end
  }
  return anaIndex.map { |k,v| v }
end

#w = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#print combine_anagrams(w)
