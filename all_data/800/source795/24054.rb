def alphabetize(words=[])
    new_words = []
    words.each do |word|
        new_words << word.downcase.split("").sort.join
    end
    new_words.uniq!
    return new_words
end

def combine_anagrams(words=[])
    uniq_alphabetized_words = alphabetize(words)
    grouped_anagrams = []
    uniq_alphabetized_words.each do |uaw|
        group = []
        words.each do |w|
            if w.downcase.split("").sort.join == uaw
                group << w
                
            end
        grouped_anagrams << group
        end
    end
    return grouped_anagrams.uniq
end

print(combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']))