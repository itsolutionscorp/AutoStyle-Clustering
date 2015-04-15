def combine_anagrams(words)
    anagrams = Hash.new()
    words.each do |word|
        key = word.downcase.chars.sort.join
        anagrams[key] = Array.new unless anagrams.has_key?(key)
        anagrams[key] << word
    end
    anagrams.values
end