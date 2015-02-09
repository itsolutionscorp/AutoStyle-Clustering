#!/usr/local/bin/ruby

def combine_anagrams(words)
  anagrams = []
  sorted_words = []
  words.each do |word|
    match = false
    wsorted = word.downcase.split(//).sort.join
    for index in 0..sorted_words.length-1
      if wsorted.==(sorted_words[index])
        match = true
        anagrams[index] << word
        break
      end
    end
    if not match
      #new anagram word
      sorted_words << wsorted
      anagram = [] 
      anagram << word
      anagrams.insert(anagrams.length, anagram)
    end
  end
  anagrams
end

puts "#{combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])}"