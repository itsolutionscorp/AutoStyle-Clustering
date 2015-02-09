# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

def combine_anagrams(words)
  h = []
  words.each do |i|
    hinner = [words.find_all{ |elem| elem.downcase.chars.sort == i.downcase.chars.sort}] 
    h += hinner    
  end

 return h.uniq
end

#puts combine_anagrams ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

