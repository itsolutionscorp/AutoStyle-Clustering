#!/usr/local/bin/ruby

def combine_anagrams(words)
    hash = {}
    anagrams = []

    words.each { |word| 
        sortedWord = word.downcase.each_char.sort.join 

        if (hash.has_key?(sortedWord))
            hash[sortedWord].push word
        else
            hash[sortedWord] = Array.new.push(word)
        end
    }

    hash.each_value { |anagramCollections|
        anagrams.push anagramCollections
    }

    return anagrams
end


a = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

puts a.inspect

