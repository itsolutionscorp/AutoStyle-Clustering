#!/usr/bin/env ruby
#
#require 'pp'

def combine_anagrams(words)
    result = {}
    words.each do |word|
        key = word.downcase.chars.sort.join("")
        if result[key]
            result[key] << word
        else
            result[key] = [ word ]
        end
    end
    result.values
end

#pp combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
