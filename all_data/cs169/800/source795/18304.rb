def combine_anagrams(words)
    h = Hash.new([])
    words.each do |w|
        id = w.downcase.chars.sort.to_s
        h[id] = h[id] + [w]
    end
    h.values
end

puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar',
                      'creams', 'scream']).inspect
