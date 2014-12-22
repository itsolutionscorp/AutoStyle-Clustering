def combine_anagrams(words)
  map = Hash.new
  
  words.each do |word|
    key = word.downcase.split(//).sort
    if map[key]
      map[key] << word
    else
      map[key] = [word]
    end
  end
  map.collect {|k,v| v}
end


#puts combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
