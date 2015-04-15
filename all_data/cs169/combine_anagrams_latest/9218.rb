#!/usr/bin/ruby

def combine_anagrams(words)
    result = {}
    words.each {|x| 
        sword = x.downcase.chars.sort.join
        if result.has_key?(sword)
            result[sword].push(x)
        else
            result[sword] = [ x ]
        end
    }
    return result.values
end

#print combine_anagrams(['cars', 'Rof', 'for', 'oFr', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
