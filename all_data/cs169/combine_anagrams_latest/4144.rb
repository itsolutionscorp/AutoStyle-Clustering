def combine_anagrams(words)
    group = {}
    words.each do |w|
        word_sorted = w.downcase.chars.sort.join
        if group.has_key?(word_sorted) then
            group[word_sorted] << w
        else
            group[word_sorted] = [w]
        end
    end
    groups = group.map do |k,v| v end
    return groups
end

if __FILE__ == $0
    puts combine_anagrams(
            ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
            ).to_s
end
