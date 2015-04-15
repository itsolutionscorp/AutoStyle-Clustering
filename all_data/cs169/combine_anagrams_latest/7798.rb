def combine_anagrams(words)
    anagram_groups = {}

    words.each { |word|
        group = word.downcase.split('').sort.join
        anagram_groups[group] ||= []
        anagram_groups[group] << word
    }

    return anagram_groups.values
end
