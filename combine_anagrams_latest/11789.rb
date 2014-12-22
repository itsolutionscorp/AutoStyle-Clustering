def combine_anagrams(words)
    h = Hash.new()
    words.each {|s|
        k = s.downcase.chars.sort.join
        if h[k] == nil
            h[k] = []
        end
        h[k] << s
    }
    results = []
    h.keys.each { |k| results << h[k] }
    return results
end

