def combine_anagrams(words)
result = words.group_by{|word| word.downcase.chars.sort}.values
end

p combine_anagrams(['Cars', 'foR', 'potatoes', 'rAcs', 'four','scar', 'creams', 'scream'])

