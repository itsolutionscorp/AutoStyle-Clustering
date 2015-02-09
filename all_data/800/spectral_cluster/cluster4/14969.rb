def is_anagram(word1, word2)
    return word1.downcase.chars.sort.join == word2.downcase.chars.sort.join
end

def combine_anagrams(words)

    anagrams = []
    words.each do |word|

        is_found = false
        anagrams.each_with_index do |group, index|
            if is_anagram(group[0], word)
                anagrams[index].push(word)
                is_found = true
            end
        end
        
        if not is_found
            anagrams.push([word])
        end
            
    end
    return anagrams
end

combine_anagrams(
['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

)

