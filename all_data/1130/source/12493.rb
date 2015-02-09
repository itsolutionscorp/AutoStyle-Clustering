def combine_anagrams(words)
    hash = {}
    words.each do |word|
        sorted_chars = word.downcase.chars.sort
        hash[sorted_chars] = [] if hash[sorted_chars].nil?
        hash[sorted_chars] << word
    end
    hash.values
end

#puts (combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']).sort == 
#    [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]].sort)? "good" : "bad"
#
#gets 