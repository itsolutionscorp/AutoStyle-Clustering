"""(c) gorlum0 [at] gmail.com"""

def combine_anagrams(words)
  words.group_by {|w| w.downcase.split('').sort }.values
end

words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  =>  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
p combine_anagrams(words)
