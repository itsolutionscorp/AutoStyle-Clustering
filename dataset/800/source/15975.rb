#!/usr/bin/ruby
# input:
#['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
    Array anagrams = []
    words.each {|x|
        flag = false
        anagrams.collect {|y|
            if x.downcase.chars.to_a.sort == y[0].downcase.chars.to_a.sort then
                y << x
                flag = true
                break
            end
        }
        unless flag; anagrams << [x] end 
    }
    anagrams
end


combine_anagrams(['hello', 'peter', 'pan', 'olleh'])
