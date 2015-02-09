def combine_anagrams(words)
  anas = Hash.new
  words.each do |w|
    key = w.downcase.scan(/\w/).sort.join
#    puts key
    if (!anas.has_key?(key))
      anas[key] = Array.new
    end
    anas[key] << w
  end
  anas.values
end

#puts combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream', 'HeLLo', 'hello']
 
