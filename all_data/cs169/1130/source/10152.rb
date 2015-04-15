def combine_anagrams(words)
    res_map = Hash.new{|hash, key| hash[key] = Array.new;} 
    words.each do |w|
        k = w.downcase.split('').sort
        res_map[k].push(w)
    end
    return res_map.values()
end

#require 'pp'
#pp combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])

