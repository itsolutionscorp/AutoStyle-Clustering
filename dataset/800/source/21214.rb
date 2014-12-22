#!/usr/bin/env ruby


def combine_anagrams(words)
    ana = []
    words.each do |i|
        cana = []
        words.each do |j|
            if j.split(%r'').uniq().downcase().sort().join() == i.split(%r'').uniq().downcase().sort().join()
                cana += [j]
            end
        end
        ana += cana
    end
    return ana
end

# input:
#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#puts combine_anagrams(words)
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter


