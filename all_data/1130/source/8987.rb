# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]

def combine_anagrams(words)
  words_uniq = words.map{ |w| w.downcase.split(//).sort.join }.uniq
  words_uniq.map{ |wu| words.select{ |w| w.downcase.split(//).sort.join == wu }}
end
