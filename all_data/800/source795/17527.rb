#!/usr/bin/ruby

def combine_anagrams(words)
  dictionary = Hash.new
  words.each { |w|
    key = w.downcase.chars.sort.join
    dictionary[key] = [] if !dictionary.has_key? key
    dictionary[key].push( w )
  }
  output = []
  dictionary.each_value{ |v| output.push( v ) }
  output
end

#combine_anagrams( ['cars', 'for', 'potatoes', 'racs', 'four', 'scar', 'creams', 'scream'] )

