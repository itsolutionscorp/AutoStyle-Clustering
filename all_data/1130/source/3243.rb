def combine_anagrams(words)
    group = {}
    words.each do |word|
        key = word.upcase.chars.sort.join
        if group.has_key? key
            group[key] << word
        else 
            group[key] = [word]
        end
    end
    return group.values
end
