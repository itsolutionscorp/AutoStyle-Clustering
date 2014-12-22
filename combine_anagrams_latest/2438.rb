#!/usr/bin/ruby

def combine_anagrams(words)
    result = Array.new
    words.each do
        | word |
        temp = words.select { |t| t.downcase.chars.sort.join == word.downcase.chars.sort.join }
        result.push(temp)
    end

    return result.uniq
end
