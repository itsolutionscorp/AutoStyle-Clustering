def combine_anagrams words
  groups = Hash.new { |hash,key| hash[key] = [] }
  words.each { |w| groups[w.downcase.chars.sort.join] << w }
  groups.values
end

puts "Testing combine_anagrams"
puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']).inspect
