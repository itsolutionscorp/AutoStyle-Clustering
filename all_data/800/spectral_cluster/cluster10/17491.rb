# Part 3: anagrams
# An anagram is a word obtained by rearranging the letters of another word.  For 
# example, "rats", "tars" and "star" are an anagram group because they are made up 
# of the same letters.
# Given an array of strings, write a method that groups them into anagram groups and 
# returns the array of groups.  Case doesn't matter in classifying string as anagrams 
# (but case should be preserved in the output), and the order of the anagrams in the 
# groups doesn't matter.
# Example:
# # input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 
# 'scream'] 
# #  => output:  [["cars", "racs", "scar"], ["four"], ["for"], 
# ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
    anagrams = []
    while not words.empty?
        w = words.pop
        
        anagram_el = []
        anagram_el << w
        
        words.each do |word|
            if is_anagram(w, word)
                anagram_el << word
            end
        end

        anagram_el.each do |word|
            words.delete(word)
        end
        
        anagrams << anagram_el
    end
    anagrams
end

def is_anagram(word1, word2)
    w1 = word1.downcase
    w2 = word2.downcase
    return w1.chars.sort.join == w2.chars.sort.join ? true : false
end

# tests
# puts is_anagram('Cars', 'racs')
# puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
# array = ['abba', 'abba', 'baab']
# array.pop
# puts array
# puts combine_anagrams(['a', 'B', 'c'])