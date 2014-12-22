def combine_anagrams(words)
    hash = {}
    words.each do |word|
        key = word.downcase.chars.sort.join
        if hash.has_key? key
            hash[key] = hash[key] + [word]
        else
            hash[key] = [word]
        end
    end
    hash.values
end
