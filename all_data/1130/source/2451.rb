
def combine_anagrams(words)
  h = Hash.new {|hash,key| hash[key] = []}
  words.each {|w| h[ w.downcase.split('').sort.join ] << w }
  h.values
end

#puts 'Testing...'
#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).to_s
#puts combine_anagrams(['Cars', 'for', 'potatoes', 'raCS', 'four','scAr', 'creams', 'scream']).to_s