def combine_anagrams(words)
ret_hash = Hash.new
words.each {|str| if (ret_hash.has_key?(str.downcase.chars.to_a.sort.to_s)) then
ret_hash.store(str.downcase.chars.to_a.sort.to_s,ret_hash[str.downcase.chars.to_a.sort.to_s] + Array[str])  else 
ret_hash.store(str.downcase.chars.to_a.sort.to_s, Array[str]) end }
ret_hash.values
end