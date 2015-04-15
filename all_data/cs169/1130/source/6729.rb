def combine_anagrams(words)
    res = {}
    words.each do |word|
        key = word.bytes.sort
        res[key] = [] if res[key] == nil
        res[key] += [word]
    end
    ret = []
    res.each {|k, v| ret += [v]}
    ret
end

# input:
# p combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
