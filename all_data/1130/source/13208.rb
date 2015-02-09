def combine_anagrams(words)
  retHash = Hash.new()
  words.each {|x| sort_str = x.downcase.chars.sort.join; if retHash.has_key?(sort_str) then retHash[sort_str] << x else  retHash[sort_str] = Array.new(1) {x} end}
  retHash.values
end

print combine_anagrams(['cars', 'for', 'potatoes', 'raCs', 'four','scar', 'creams', 'scream'])
