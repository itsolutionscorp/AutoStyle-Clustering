def combine_anagrams(words)
  groups=Hash.new
  words.each do |word|
     alpha_word = word.downcase
     alpha_word = alpha_word.split('').sort!.join('').delete(" ")
    if groups.key?(alpha_word) ==false
      groups[alpha_word]=Array.new
   end
   groups[alpha_word]=groups[alpha_word]+[word]   
   puts groups[alpha_word]
  end
 
  return groups.keys.sort_by {|key| key.to_i}.map {|key| groups[key]}
end
