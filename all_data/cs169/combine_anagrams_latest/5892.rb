ary = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  hsh = {}
  words.each do |x|
	key = x.downcase.chars.sort.join
	if hsh[key].nil?
		hsh[key] = [x]
	else
		hsh[key].push x
	end
  end
  return hsh.collect { |key, val| val }
end

puts combine_anagrams(ary).to_s