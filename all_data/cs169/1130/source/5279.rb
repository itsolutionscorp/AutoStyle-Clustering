# Anagrams
# Jose Daniel Gamboa
# josedanielgamboa@gmail.com
#

def combine_anagrams(words)
  @words_hash = words.group_by {|word| word.downcase.chars.sort.join}; 
  @words_hash.default = []  
  return @words_hash.values
end

print "['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] >> ", combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']);
print "\n";
print "['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] >> ", combine_anagrams(['CARS', 'For', 'PotaToes', 'racs', 'four','scar', 'creams', 'scream']);
print "\n";


# input:   
#   => output:    [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]] 
# HINT: you can quickly tell if two words are anagrams by sorting their 
#   letters, keeping in mind that upper vs lowercase doesn't matter 
 