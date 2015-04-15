# Group an array of words into anagram groupings
def combine_anagrams(words)
    groups = {}
    words.each do |word|
        anagram = word.downcase.split(//).sort.join
        (groups[anagram] ||= []) << word
    end

    return groups.values
end
