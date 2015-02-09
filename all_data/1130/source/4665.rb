#! /usr/bin/ruby

## Part 3 ##

# a
def combine_anagrams(words)
    anagrams = Hash.new
    words.each { |word| anagrams[word.downcase.chars.sort.join] = anagrams.fetch(word.downcase.chars.sort.join, []) << word }
    return anagrams.values
end
