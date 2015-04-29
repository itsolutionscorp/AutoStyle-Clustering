def combine_anagrams(words)
    cage = Hash.new()
    words.each do |word|
        sorted_word = word.split('').sort.join
        if cage.has_key?(sorted_word)
            cage.store(sorted_word, cage.fetch(sorted_word).push(word))
        else
            cage.store(sorted_word, Array.new(1,word))
        end
    end
    return cage.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])