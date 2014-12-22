def combine_anagrams(words)
   keys = words.map {|elt| sort_string(elt)}
   keys = keys.uniq
   hash = Hash.new
   keys.each {|k| hash[k]=[]}
   words.each {|word| hash[sort_string(word)] << word}
   hash.values
end
def sort_string(s)
  s.downcase.chars.sort.join
end
