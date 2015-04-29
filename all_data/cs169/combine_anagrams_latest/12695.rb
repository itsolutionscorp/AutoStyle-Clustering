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

my_words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 
'scream']

arrays = combine_anagrams(my_words)
arrays.each do |a|
    puts(a)
end