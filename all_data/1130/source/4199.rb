#!/usr/bin/ruby

def combine_anagrams(words)

    anagrams = Array.new(0)
    words.each do |wordA|
        subArray = []
        words.each do |wordB|
            if (wordA.downcase.split('').sort == wordB.downcase.split('').sort)
                subArray.push(wordB)
            end
        end
        anagrams.push(subArray.sort)
    end
    return anagrams.uniq
end

#words_list = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

#puts combine_anagrams(words_list)
