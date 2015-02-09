#!/usr/bin/ruby

require 'test/unit'

def normalize(word)
    return word.downcase.chars.sort.join
end

def combine_anagrams(words)
    groups = []
    while !words.empty?
        cl = normalize(words[0])
        group = words.select{ |word| normalize(word) == cl }
        words.delete_if{ |word| group.include?(word) }
        groups.push(group)
    end
    return groups
end 

class TestCombineAnagrams < Test::Unit::TestCase
    def test_combine_anagrams
        data = ['Cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 
            'scream']
        puts combine_anagrams(data).inspect
    end
end
