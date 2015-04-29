def combine_anagrams(words)
    sorted_words = words.map {|word|
        word.downcase.split('').sort.join('')
    }.uniq
    
    combined_words =[]
    sorted_words.each { |sort|
        combined_words << words.select { |word| 
            word.downcase.split('').sort.join('') == sort }
    }
    return combined_words

end

test1=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream','cars']

print combine_anagrams(test1)
