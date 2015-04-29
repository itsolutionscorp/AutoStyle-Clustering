require 'pp'
# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
    cage = Hash.new()
    words.each do |word|
        sorted_word = word.split('').sort.join
        if cage.has_key?(sorted_word)
            cage.store(sorted_word, cage.fetch(sorted_word).push(word))
        else
            cage.store(sorted_word, Array.new(1,word))
        end
    end
    return cage.values
end

pp combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream'])
pp combine_anagrams(['a', 'a', 'a', 'aB', 'Ba', 'ab', 'scream'])
