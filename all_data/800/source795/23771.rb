def combine_anagrams(words)
    result = {}
    for word in words
        result[sorted_word(word)] = [word] unless result[sorted_word(word)]
        for word2 in words
            next if word == word2
            if result[sorted_word(word2)] && !result[sorted_word(word2)].include?(word2)
                result[sorted_word(word2)] << word2
            end
        end
    end
    [result.values]
end

def sorted_word(w)
    w.downcase.split('').sort.join
end
