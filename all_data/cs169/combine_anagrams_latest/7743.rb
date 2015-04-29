require 'pp'

def norm(word)
  newword = ''
  offset = 0
  ("a".."z").each do |c|
     while word.index(c, offset) != nil
       newword = newword + c
       offset = word.index(c, offset) + 1
     end
     offset = 0
  end
  return newword
end 

def combine_anagrams(words)
  ordered = Hash.new{[]}
  anagrams = Array.new
  words.each do |word|
     norm = norm(word.downcase)
     if not ordered.has_key?(norm) then
        ordered[norm] = Array.new
     end 
     ordered[norm] << word
  end
  ordered.each do |key, value|
     anagrams << value
  end 
  return anagrams
end

w = ['Cars', 'foR', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 
PP.pp(combine_anagrams(w))
