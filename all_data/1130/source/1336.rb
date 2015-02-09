def combine_anagrams(words)
    anagrams = {}
    words.each do |word|
        normalized = word.downcase.split('').sort.join
        (anagrams[normalized] ||= []) << word
    end
    anagrams.values
end
