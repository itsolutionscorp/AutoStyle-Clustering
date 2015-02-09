def combine_anagrams(words)
    anaglist = Hash.new([])
    anaggrps = Array.new
    words.each do |w|
        idx = w.downcase.split(//).sort.join
        anaglist[idx] += [w]
    end
    anaglist.each_value do |v|
        anaggrps += [v]
    end
    return anaggrps
end
