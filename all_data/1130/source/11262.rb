def combine_anagrams(words)
    result = Hash.new([])
    words.each do |x|
        a = x.downcase.split(//).sort.join
        puts a
        result[a] += [x]
    end
    result.values
end

# input:
#combine_anagrams (['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]