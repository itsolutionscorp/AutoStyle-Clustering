def combine_anagrams(words)
    anagrams = Hash.new{|h, k| h[k] = []}
    words.each { |word| anagrams[word.downcase.chars.sort.join] += [word] }
    return anagrams.values
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])
