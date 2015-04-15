#!/usr/local/bin/ruby$

def combine_anagrams(words)
  anagrams = Array.new(0)
  puts "input: #{words}\n"

  words.each do |word1|
    tmp = []
    words.each do |word2|
      if (word1.downcase.split(//).sort == 
          word2.downcase.split(//).sort)
        tmp.push(word2)
      end
    end
   anagrams.push(tmp)
  end
  puts "=> output: #{anagrams.uniq}\n"
  return anagrams.uniq
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'])


