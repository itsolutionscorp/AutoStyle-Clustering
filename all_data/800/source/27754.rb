
# input: 
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter


def combine_anagrams(words)
    hsh = {}
    words.each do |word|
        key = word.chars.sort_by(&:downcase).join
        if !hsh.has_key? key
            hsh[key] = []
        end

        hsh[key] << word
    end

    result = []

    hsh.keys.each do |k|
        result << hsh[k]
    end

    return result

end


#print combine_anagrams ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 

#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
