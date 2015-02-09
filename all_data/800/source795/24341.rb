def wordsort(word)
    return word.downcase.chars.sort
end

def combine_anagrams(words)
    anagrams = Array.new
    uniques = words.map { |w| wordsort(w) }.uniq
    uniques.each do |u|
        anagrams << words.find_all { |w| wordsort(w) == u }
    end
    return anagrams
end
