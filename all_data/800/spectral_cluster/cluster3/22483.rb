require 'pp'

def combine_anagrams(words)
    # The output format is:
    # [ ana_group_0, ana_group_1, ..., ana_group_N ]
    # where an anagram group can consist of one or more words
    
    # Words are anagrams if their sorted strings are identical
    hash = Hash.new()
    words.each do |word|
        sorted = word.downcase().split("").sort().join()
        hash[sorted] = [] unless hash[sorted]
        hash[sorted].push(word)
    end
    array = []
    hash.each {|key, value|
        array.push(value)
    }
    return array
end

pp combine_anagrams(["Wee", "wah", "eew", "Droop", "prood"])
