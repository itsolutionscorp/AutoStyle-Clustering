def combine_anagrams(words)
    anagrams = {}
    words.each do |word|
        key = word.downcase.scan(/./).sort.join
        if anagrams[key]
            anagrams[key] << word
        else
            anagrams[key] = [word]
        end
    end
    anagrams.map {|k,v| v }
end
