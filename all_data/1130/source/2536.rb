#!/usr/bin/ruby

def combine_anagrams(words)
  groups = []
  words.each do |word|
    is_added = false
    groups.map do |group|
      if group[0].downcase.chars.to_a.sort == word.downcase.chars.to_a.sort
        group.push(word)
        is_added = true
        break
      end
    end  
    groups.push([word]) if !is_added
  end
  groups
end


