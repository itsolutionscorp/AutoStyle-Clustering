def combine_anagrams(words)
    combined_words = {}
    words.each do |word|
        normalized = word.downcase.split('').sort.join
        if combined_words.has_key?(normalized)
            combined_words[normalized] << word
        else
            combined_words[normalized] = [word]
        end
    end
    result = []
    combined_words.each do |key,group|
        result << group
    end
    return result
end


if __FILE__ == $0
    words = ['ababa',  'meme', 'mmee', 'babaa','eemm']
    combined =  combine_anagrams(words)
    combined.each do |a|
        puts a
        puts '--'
    end
end
