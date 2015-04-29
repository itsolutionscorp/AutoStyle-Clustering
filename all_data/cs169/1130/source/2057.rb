
def combine_anagrams(words)

ana = Hash.new()
    words.each do |word|
        sorted_word = word.downcase.split('').sort.join
        if ana.has_key?(sorted_word)
            ana.store(sorted_word, ana.fetch(sorted_word).push(word))
        else
            ana.store(sorted_word, Array.new(1,word))
        end
    end
    return ana.values

end



data =  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']

puts combine_anagrams(data)
