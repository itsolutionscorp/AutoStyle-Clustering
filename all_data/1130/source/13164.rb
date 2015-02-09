def combine_anagrams(words) 
    words_expanded = Array.new
    words.each_with_index do |word,i|
        words_expanded[i] = Array.new
        word.each_char do |char|
            words_expanded[i] << char.downcase 
        end
    end
    
    result_hash = Hash.new

    words_expanded.each_with_index do |word,i|
        unless result_hash.has_key?(word.sort.to_s)
            result_hash[word.sort.to_s] = []
        end
            result_hash[word.sort.to_s] << words[i]
    end

    result = Array.new
    
    result_hash.each_value do |group|
        result << group
    end

    return result
end