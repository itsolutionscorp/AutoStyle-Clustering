 #(a)

def combine_anagrams(words)
  groups = Hash.new([])
  words.each do |x|
    i = x.downcase.scan(/./).sort.join
    groups[i] = groups[i] + [x] 
  end
  groups.values
end

#test
# input: 
#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']) 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]

