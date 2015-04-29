def combine_anagrams(words)
   words.group_by { |w| w.downcase.split('').sort!.join }.values
end

# puts combine_anagrams([ 
#     'cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream' 
# ]).inspect
# -> [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
