#!/usr/bin/env ruby

def combine_anagrams(words)
  sorted = Array.new
  rtn = Array.new
  if words.kind_of?(Array) 
    words.each do |word|
      srt = word.downcase.chars.sort.join
      idx = sorted.index(srt)
      if idx
	  rtn[idx] = [rtn[idx], word].flatten 
      else
          sorted.push(srt)
          rtn.push(word)
      end
    end
  else
      return [words]
  end

  #puts words
  #puts " "
  #puts sorted
  #puts " "
  #puts rtn
  return rtn
end

#test = ["z","a","b","c", "A", "BA", "Ab", "bA", "AA"]
#test = "this"
#output = combine_anagrams(test)
#puts output
#output.each do |o|
#    puts o
#    puts " "
#end 

# input: 
#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
#test = [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
#puts test
# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter
