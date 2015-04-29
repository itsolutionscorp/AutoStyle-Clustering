def combine_anagrams(words)
    hash = Hash.new{|h, k| h[k] = []}
    words.each do |entry|
        word = entry.clone
        hash[word.downcase.split("").sort].push(word) 
    end   
   hash.values
end
