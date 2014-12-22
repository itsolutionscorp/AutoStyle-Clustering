#!/usr/bin/ruby

def combine_anagrams(words)
    outputArray = []
    words.each do |word|
        found = false;
        outputArray.each do |internalArray|
            if word.downcase.chars.sort.join == internalArray[0].downcase.chars.sort.join
                internalArray << word
                found = true;
            end
        end
        if (!found)
            newArray = [word]
            outputArray << newArray
        end
    end
    return outputArray
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
