def combine_anagrams(words)
    anagrams = {}
    words.each do |word|
        key = word.downcase.chars.sort.join
        if anagrams.key?(key)
            anagrams[key] << word
        else
            anagrams[key] = [word]
        end
    end
    
    result = []
    anagrams.each_key do |key|
        result << anagrams[key]
    end
    result
end