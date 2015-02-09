def combine_anagrams(words)
    words_hash = words.group_by {|word| word.chars.sort}; words_hash.default = []
end
