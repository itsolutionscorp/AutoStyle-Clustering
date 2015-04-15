# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

def combine_anagrams(words)
  words.group_by { |elt| elt.downcase.chars.sort }.values
end
