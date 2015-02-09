def combine_anagrams(words)
  anagrams = Hash.new()
  final = []
  
  words.each do |w|
    key = w.downcase.chars.sort.join
    
    if anagrams.has_key?(key)
      anagrams[key] << w
    else
      anagrams[key] = Array.new(1, w)
    end
  end
  
  anagrams.each do |key, value|
    final << value
  end
  
  final
end

#combine_anagrams(['Cars', 'For', 'racs', 'four', 'scar']) 
