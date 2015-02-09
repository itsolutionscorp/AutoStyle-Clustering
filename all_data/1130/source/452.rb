# input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
  dict = {}
  words.each_with_index do |v, i|
  	key = v.downcase.chars.sort{ |a, b| a.casecmp(b) } .join
  	if dict.has_key?(key)
  		dict[key].push(v)
  	else
  		dict[key]=[v]
  	end
  end

  out = []
  dict.each do |k, v|
  	out.push(v)
  end
  return out
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
puts combine_anagrams(words)