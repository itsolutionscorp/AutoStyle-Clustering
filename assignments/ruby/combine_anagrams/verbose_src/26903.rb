# input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	h = Hash.new{|hash, key| hash[key] = Array.new;}
	words.each do |word| h[word.downcase.split(//).sort.join] << word end
	h.values  
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
