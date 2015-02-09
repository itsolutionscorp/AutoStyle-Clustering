def combine_anagrams(words)
    groups = {}
    words.each do |word|
        ordered = word.downcase.chars.sort.join
        groups[ordered] = groups.fetch(ordered,[]).concat([word])
    end
    return groups.values
end