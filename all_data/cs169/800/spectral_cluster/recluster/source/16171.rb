def combine_anagrams(words)
    dicts = words.map {|word| {word.downcase.chars.sort.join => words.index(word)}}
    anagrams = {}
    dicts.each do |dict|
        if  anagrams[dict.keys[0]]
            anagrams[dict.keys[0]] << words[dict.values[0]]
        else
            anagrams[dict.keys[0]] = [words[dict.values[0]]]
        end
    end
    return anagrams.values
end
