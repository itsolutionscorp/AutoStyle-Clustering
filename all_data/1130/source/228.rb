def combine_anagrams(words)
    anagrams = {}
    words.each do |word|
        key = word.downcase.chars.sort
        if anagrams.has_key?(key)
            anagrams[key] << word
        else
            anagrams[key] = [word]
        end
    end
    anagrams.values
end