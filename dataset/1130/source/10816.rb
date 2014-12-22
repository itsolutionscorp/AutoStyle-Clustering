def combine_anagrams(words)
    w = Hash.new
    words.each do |word|
        key = word.downcase.chars.sort.join
        if w.key?(key) == false
            w[key] = [word]
        else
            w[key].push(word)
        end
    end
    w.values
end

