words = ['Cars', 'ofr','rof', 'for', 'potatoes', 'raCs', 'four','Scar', 'creams', 'scream']

def combine_anagrams(words)
    anagrams = {}
    words.each do |w|
        word = []
        w.each_byte do |b|
           word << b.chr.downcase
        end

        if anagrams[word.sort.join('')].nil?
            anagrams[word.sort.join('')] = []
        end
        anagrams[word.sort.join('').downcase] << w
    end
    anagrams.values
end
p combine_anagrams(words)
