def combine_anagrams(words)

hash = words.group_by {|word| word.downcase.chars.sort}
arr = hash.values
return arr
end

print combine_anagrams(['cArs', 'for', 'potatoes', 'racs', 'four','scar', 'creaMs', 'scReam'])
