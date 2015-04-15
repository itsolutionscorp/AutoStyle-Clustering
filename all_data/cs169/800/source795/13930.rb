def combine_anagrams(words)
    groups = []
    words.each do |word|
        found = false
        groups.each do |group|
            if normalize(group[0]) == normalize(word)
                found = true
                group.push word
            end
        end
        groups.push [word] unless found
    end
    groups
end

def normalize(word)
    return word.downcase.split('').sort.join
end

require 'pp'
pp combine_anagrams ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
