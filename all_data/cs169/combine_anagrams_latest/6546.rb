def combine_anagrams(words)
    anagrams = Hash.new()
    words.each do |word|
        sorted_word = word.split('').sort.join
        if anagrams.has_key?(sorted_word)
            anagrams.store(sorted_word, anagrams.fetch(sorted_word).push(word))
        else
            anagrams.store(sorted_word, Array.new(1,word))
        end
    end
    return anagrams.values
end