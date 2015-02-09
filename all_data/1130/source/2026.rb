#!/usr/bin/ruby

def combine_anagrams(words)

    anagram_hash_groups = Hash.new { |hash, key| hash[key] = [] }
    words.each do |word|
        anagram_hash_groups[word.downcase.chars.sort.join].push(word)
    end

    anagram_groups = Array.new
    anagram_hash_groups.each do |key, value|
        anagram_groups.push(value)
    end

    anagram_groups
end
