def combine_anagrams(words)
word_hash = Hash.new(0)
words.each do |word| 
  sorted_word = word.downcase.chars.sort.join
  if word_hash.has_key?(sorted_word)
      word_hash[sorted_word] = word_hash[sorted_word].concat([word])
 	else
        word_hash[sorted_word] = [word]
        end 
  end
 return word_hash.values
end