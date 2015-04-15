def combine_anagrams(words)
    anagrams = Hash.new("")
    words.each { |word| if anagrams[word.downcase.chars.sort.join] == "" then anagrams[word.downcase.chars.sort.join] = Array.new end; anagrams[word.downcase.chars.sort.join].push(word) }
    result = Array.new
    anagrams.each{ |key, value| result.push(value)}
    return result
end

print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

