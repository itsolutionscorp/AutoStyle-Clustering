


def combine_anagrams(words)
    anas = []
    words.each do |word|
        found = anas.find {|ana| ana[0].downcase.split(//).sort == word.downcase.split(//).sort}
        if found
            found << word
        else
            anas << [word]
        end

    end
    anas
end



words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
p combine_anagrams(words)

# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
