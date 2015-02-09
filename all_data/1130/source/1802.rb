# encoding: UTF-8
def combine_anagrams(words)
    anagrams = {}
    words.each do |word|
        anagram = word.downcase.chars.sort.join
        if anagrams.has_key?(anagram)
            anagrams[anagram] << word
        else
            anagrams[anagram] = [word]
        end
    end
    return anagrams.values
end

#words = ['Cars', 'cars', 'for', 'A', 'a', 'a','potatoes', 'rAcs', 'four','scar', 'creams', 'scream']
#combine_anagrams(words)