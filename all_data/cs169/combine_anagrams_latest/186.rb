def combine_anagrams(words)
    h = Hash.new([])
    words.each do |x|
        h[x.downcase.chars.sort.join] += [x]
    end
    return h.values
end
