# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

data = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
data2 = ['Cars', 'for', 'potatoes', 'racs', 'four','scAr', 'creamS', 'scream']


def combine_anagrams(words)
    # <YOUR CODE HERE>
    
    res = []
    
    sorted_chars = words.map {|w| w.chars.sort.join }
    
    #~ for w in sorted_chars
        #~ puts w
    #~ end
    
    #~ print "sorted_chars: "
    #~ puts sorted_chars.inspect
    #~ 
    #~ puts words.select { |w| w.chars.sort.join ==  }.inspect
    
    for w in words
        res << words.find_all do |item| 
          item.chars.map {|c| c.downcase}.sort.join == w.chars.map {|c| c.downcase}.sort.join
        end
    end
    res.uniq
    #~ "abc".chars.to_a.permutation.map &:join # array of all permutations of the current string
end

puts combine_anagrams(data).inspect
puts combine_anagrams(data2).inspect
