# input:
wordser = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
# <YOUR CODE HERE>
    hasher={}
    anagrams=[]
    num=0
    words.each do |word|
        sorted_word = word.chars.sort { |a, b| a.casecmp(b) } .join
        if hasher.has_key?(sorted_word.downcase)
            anagrams[hasher[sorted_word.downcase]] << word
        else
            hasher[sorted_word.downcase]=num
            anagrams[num] = [word]
            num += 1
        end
    end
    return anagrams
end

#puts 'cars'.chars.sort { |a, b| a.casecmp(b) } .join
#print combine_anagrams(wordser)
