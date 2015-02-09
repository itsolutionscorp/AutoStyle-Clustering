def combine_anagrams(words)
  #hash = Hash.new([""])
  hash = {}
  words.each do |w|
    hash[w.downcase.chars.sort.join] ||= []
    hash[w.downcase.chars.sort.join] << w
    #puts hash[w.downcase.chars.sort.join]
  end
  
  hash.values
end

p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
hash = Hash.new()
