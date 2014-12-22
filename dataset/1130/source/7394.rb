# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter


def combine_anagrams(words)
    key = Array.new
    output = Array.new   
    words.each do
        |word|
        temp =  word.downcase.chars.sort.to_s
        if key.index(temp)
            output[key.index(temp)] << word            
        elsif
            output << [word]
            key << temp    
        end
        
    end
   return output
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
