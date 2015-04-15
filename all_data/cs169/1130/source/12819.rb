def combine_anagrams(words)
    anagrams = Hash.new
    words.each do |word|
        sortedWord = word.downcase.split(//).sort.join
        
        if !anagrams.has_key? sortedWord
            anagrams[sortedWord] = []
        end
        
        anagrams[sortedWord].push(word)
    end
    
    return anagrams.values
end