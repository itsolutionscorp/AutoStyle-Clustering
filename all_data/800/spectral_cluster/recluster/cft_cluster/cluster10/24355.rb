#!/usr/bin/env ruby

def combine_anagrams(words)
  anagrams = Hash.new{|h, k| h[k] = []}

  words.each do |word|
    key = word.downcase.chars.sort.join
    puts "word=#{word} key=#{key}"
    anagrams[key].push word
  end
  puts "anagrams: "+anagrams.inspect
  return anagrams.values
end

if __FILE__ == $0
  puts "Hi!"
  t = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
  puts combine_anagrams(t).inspect
  puts "Done."
end
