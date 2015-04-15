def combine_anagrams(words)
    result = Hash.new()
    words.each do |word|
        sorted_word = word.downcase.split('').sort.join
        if result.has_key?(sorted_word)
            result.store(sorted_word, result.fetch(sorted_word).push(word))
        else
            result.store(sorted_word, Array.new(1,word))
        end
    end
    return result.values
end

# print combine_anagrams(['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])