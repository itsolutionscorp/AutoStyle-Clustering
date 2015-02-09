def combine_anagrams(words)
    anagrams = []
    words.each do |w|
        a = words.select { |a| a.downcase.split("").sort.join == w.downcase.split("").sort.join }
        words = words - a
        anagrams = anagrams + [a]
    end
    anagrams.reject { |a| a == [] }
end
