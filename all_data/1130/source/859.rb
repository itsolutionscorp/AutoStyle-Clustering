def combine_anagrams(data)
    map = {}
    data.each do |word|
        sorted = word.downcase.split('').sort
        map.has_key?(sorted) ? map[sorted] << word : map[sorted] = [word]
    end
    map.values
end
