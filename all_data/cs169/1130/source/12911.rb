#!/usr/local/bin/ruby

def combine_anagrams(words)
  temp_words = []
  words.each() { |str| x=str.downcase() ;  temp_words.push(x.chars.sort.join) }
  
  h = Hash.new
  
  keys = temp_words.uniq
  keys.each { |e| h.store(e, [])}
  
  words.each do
    |e| h[e.downcase().chars.sort.join] = h[e.downcase().chars.sort.join].push(e)
  end  

  k = []
  keys.each {|e| k.push(h[e])}
  
  #k.each do
    #|e| puts "<<#{e}>>"
  #end
  
  return k
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'CreAms', 'scream'])

