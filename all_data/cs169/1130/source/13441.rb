# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  # sort - case insensitive
  hashArray = Hash.new
  bigArray = Array.new
  words.each do |word|
    theKey = word.chars. sort{ |a, b| a.casecmp(b) } .join.downcase
  	if(hashArray["#{theKey}"])
  		hashArray["#{theKey}"] = hashArray["#{theKey}"] << word 
  	else
  		hashArray["#{theKey}"] = Array.new << word
  	end
  end
  return hashArray.values
end

p combine_anagrams(['cars', 'for', 'potatoes', 'raCs', 'four','scar', 'creams', 'scream'] )


