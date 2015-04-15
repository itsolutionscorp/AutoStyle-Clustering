# Software Engineering for Saas
# HW 1: Ruby calisthenics
# Part 3: anagrams
#
# In 2012-06-12
# By Thiago Kronig <thiagokronig@gmail.com>

# input:
# ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
# ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
    
    # new map with the empty list as default value for all keys
    map = Hash.new
    
    # For each word, takes the uppedcase sort of the word as key
    # and add the word to the array mapped by key.
    # If theres no array mapped, map to a new Array.
    words.each do |word|
        key = word.upcase.chars.sort.join
        if map[key].nil?
            map[key] = Array.[] (word)
        else
            map[key] << word
        end
    end
    
    adapter = Array.new
    map.each_value { |value| adapter << value }
    
    return adapter
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])

