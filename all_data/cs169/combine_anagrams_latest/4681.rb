def combine_anagrams(words)
    words.group_by{|w| w.downcase.chars.sort.to_s}.values
end

print combine_anagrams(['cars','for','potatos','racs','four','scar','creams','scream'])
