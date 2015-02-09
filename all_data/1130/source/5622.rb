def combine_anagrams(words)
return words.group_by{|word| word.downcase.chars.sort}.values
end
