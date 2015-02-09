# input:
#input = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"],
#["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
    anagrams = Hash.new()
    words.each do |word|
        letters = word.downcase.gsub(/[^a-z]/, "").split("").sort.join
        anagrams[letters] = Array.new unless anagrams.include?(letters)
        anagrams[letters] << word
    end
    anagrams.values
end

#puts combine_anagrams(input).inspect