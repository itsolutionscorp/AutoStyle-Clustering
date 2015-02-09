def combine_anagrams(words)
    combos = Hash.new([])
    words.each do |word|
        key = word.downcase().split('').sort().join()

        if not combos.has_key? key
            combos[key] = []
        end

        combos[key] << word
        puts key
    end
    combos.values()
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
