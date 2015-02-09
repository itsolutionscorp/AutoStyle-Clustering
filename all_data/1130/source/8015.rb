def combine_anagrams(words)
  ht = Hash.new {|h,k| h[k]=[]}
  words.each do |entry|
    word = entry.clone
    ht[word.downcase.split("").sort].push(word)
  end
  ht.values
end

p combine_anagrams(['cARs', 'FOR', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
