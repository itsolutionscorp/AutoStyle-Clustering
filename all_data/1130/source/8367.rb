def combine_anagrams(words)
    anagramsHash = Hash.new
    words.each { |word|
        k = word.downcase.chars.sort.join
        anagramsHash.key?(k) ? anagramsHash[k] = anagramsHash[k].concat([word]) : anagramsHash.store(k, [word])
    }
    anagramsHash.values
end