#!/usr/bin/ruby

def combine_anagrams(words)
  ordered = Array.new(words)
  anagramArray = Array.new
  anagramMap = Hash.new
  words.each_index do |x|
    ordered[x] = words[x].downcase.unpack("c*").sort.pack("c*")  
    #puts ordered[x]
    #puts words[x] 
  end
  
  ordered.each_index do |x|
    orderedLetters = ordered[x]
    word = words[x]
    if anagramMap.has_key?(orderedLetters)
       anagramMap[orderedLetters] = anagramMap.fetch(orderedLetters).push(word)
    else
       array = Array.new()
       array[0] = word
       anagramMap[orderedLetters] = array
    end
  end
  
  #puts ordered
  #puts words
  #puts anagramMap
    
  anagramMap.keys.each do |x|
    anagramArray.push(anagramMap.fetch(x))
    #puts anagramMap.fetch(x)
    #puts "new"
  end
  #puts anagramArray
  return anagramArray
end

example = ["cars", "for", "racs", "scream", "creams", "scar"]

combine_anagrams(example)
