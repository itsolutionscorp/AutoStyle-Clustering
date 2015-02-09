#!/usr/local/bin/ruby

def combine_anagrams(words)
  #   <YOUR CODE HERE>
  wkeys = words.collect {|w| w.downcase.chars.sort.join}

  print "testing"
  print words
  puts
  print wkeys
  puts
  print "test ends\n"

  hash = Hash.new
  for i in 0..(words.length-1) do

   key = wkeys[i]

   values = hash[key] != nil ? hash[key] : []
   values << words[i]
   hash[key] = values

  end

  array = []
  hash.each_value {|a| array << a}
  array
end


def test ()
# input: 
print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
puts
#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter  
end

#test
