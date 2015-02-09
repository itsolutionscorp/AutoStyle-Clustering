# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter


def combine_anagrams(words)
#  <YOUR CODE HERE>
    hash = Hash.new{|h, k| h[k] = []}
    words.each do |entry|
        word = entry.clone
        hash[word.downcase.split("").sort].push(word) 
#        word.split('').sort == entry.split('').sort return entry
    end   
   hash.values
end
words =  ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(words).inspect
puts 'expected [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]'
