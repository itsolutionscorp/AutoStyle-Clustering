def combine_anagrams(words)
  wordCount = Hash.new { |hash, key| hash[key] = [] }
  words.each do |word|
    orgWord = word;
  #  puts orgWord

    sword = word.downcase.chars.sort.join
    puts sword
    wordCount[sword].push(orgWord)
   
 end

  return wordCount.values
  
end

#values = combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
values = combine_anagrams(['A', 'aaaa', 'AAAA', 'a'])
puts values.length;
puts values