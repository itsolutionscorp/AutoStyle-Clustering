def combine_anagrams(words)
    arr = Hash.new()
    words.each do |word|
        sorted_word = word.split('').sort.join
        if arr.has_key?(sorted_word)
            arr.store(sorted_word, arr.fetch(sorted_word).push(word))
        else
            arr.store(sorted_word, Array.new(1,word))
        end
    end
    return arr.values
end