#!/usr/bin/env ruby





def combine_anagrams(words)
  out=[]
 words.each do |w|
    r=/#{w.chars.sort.to_s}/i;puts r
    if !out.flatten.include?(w) 
        out << words.select{ |word| word.downcase.chars.sort.to_s == w.downcase.chars.sort.to_s}
    end
 end
 return out
end
