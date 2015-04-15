def combine_anagrams(words)
    anagrams = []
    found = {}
    words.sort!
    words.each do |word|
        if found[word].nil?
            temp = []
            words.each do |w|
                if found[w].nil?
                    temp << w if w.downcase.split("").sort == word.downcase.split("").sort
                end
            end
            temp.each { |t| found[t] = true }
            anagrams << temp
        end
    end
    return anagrams
end

def find_anagrams(word, words)
    anagrams = []
    words.each do |w|
        anagrams << w if w.downcase.split("").sort == word.downcase.split("").sort
    end
    puts "#{anagrams}" if anagrams.length > 1
    return anagrams
end


# input: 
#puts "Final Result:\n#{answer}"
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], 
#answer = combine_anagrams ["creams", "scream"]
#puts "Final Result:\n#{answer}"
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter