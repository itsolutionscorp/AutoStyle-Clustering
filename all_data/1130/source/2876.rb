def combine_anagrams(words)
    anagrams = Array.new
    words.each { |word|
        if anagrams.length == 0
            anagrams << Array.new(1,word)
        else
            added = false
            anagrams.each { |anagram|
                if anagram[0].downcase.chars.sort.join ==
                    word.downcase.chars.sort.join
                    anagram << word
                    added = true
                end
            }
            unless added
                anagrams << Array.new(1,word)
            end
        end
    }
    return anagrams
end
