def combine_anagrams(words)
  anagram_groups = {}
  words.each do |w|
    key = w.downcase.chars.sort.join
    if anagram_groups.has_key?(key) == false
      anagram_groups[key] = Array.new 
    end
    anagram_groups[key] << w 
  end
  result = Array.new
  anagram_groups.each_key do |g|
    result << anagram_groups[g]  
  end 
  return result
end

#puts combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'Scream'] ).to_s