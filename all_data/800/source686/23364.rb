def combine_anagrams(words)
  hash = Hash.new
  hash.default = Array.new
  words.map{|x| key = x.split('').sort.join; hash[key]+=[x] }
  hash.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).inspect
