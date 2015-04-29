def combine_anagrams(words)
    anagrams = Hash.new
    words.each { |word| 
        key = word.downcase.chars.sort.join
        anagrams[key] = Array.new unless anagrams[key]
        anagrams[key] += [word]
    }
    return anagrams.values
end


