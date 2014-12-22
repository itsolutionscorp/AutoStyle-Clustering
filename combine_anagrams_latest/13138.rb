#!/usr/bin/env ruby

def combine_anagrams(words)
    stack=[]
    if words.length==0
        return []
    end
    words.each do |word|
        a=word.downcase.chars.sort.join
        stack.push( words.select { |item| a==item.downcase.chars.sort.join} )
    end
    if stack.length==0
        return [words]
    end
    return stack.uniq
end