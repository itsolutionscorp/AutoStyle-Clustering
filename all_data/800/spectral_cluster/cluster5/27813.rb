# input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
	h = Hash.new { |hash,val| hash[val]=[] }
	words.each { |val| h[val.downcase.chars.sort.join] << val }
	h.values
end

arr = ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream']

puts combine_anagrams arr
puts arr << ['dfgsdfg']