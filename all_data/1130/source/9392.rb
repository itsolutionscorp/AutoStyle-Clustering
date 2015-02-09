def combine_anagrams(words)
##    words.each{|item| item.downcase!}
    h = Hash.new
    words.each{|item|
        idx = item.chars.sort.join.downcase
        h.has_key?(idx) ? h[idx] += [item] : h[idx] = [item]
        }
    h.values
end