# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
# [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
def combine_anagrams(words)
  words.inject(Hash.new([])) {|result, x| result[x.downcase.each_char.sort] += [x]; result}.values
end
 
=begin
w = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'Creams', 'scream']
print combine_anagrams(w)
=end
