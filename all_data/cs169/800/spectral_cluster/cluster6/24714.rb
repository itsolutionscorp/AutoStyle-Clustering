#Part 3: anagrams

def combine_anagrams(words)
  result=Hash.new
  words.each{|w| key=order_characters(w);result[key]||=[]; result[key] << w}
  result.values
end

def order_characters(words)  
  words.downcase.chars.to_a.sort.join()
end  