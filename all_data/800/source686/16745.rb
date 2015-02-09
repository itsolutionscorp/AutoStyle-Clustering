def combine_anagrams(words)
 return if words == nil

 h = Hash.new(Array.new(1, ""))
 words.each do |word|
   next if word == nil

   key = word.downcase.split(//).sort.join
   if (h.has_key?(key))
     h[key] = h.fetch(key) << word
   else
     h[key] = [word]
   end
 end
 return h.values
end

# Test
words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts combine_anagrams(words).inspect

# Test empty array
words = [nil]
puts combine_anagrams(words).inspect

words = nil
puts combine_anagrams(words).inspect

# Test single character case
words = ['a', 'a', 'b', 'c', 'd', 'c', 'c', 'd', 'f']
puts combine_anagrams(words).inspect

# Test a complicated case of some repeated anagrams
words = ['cars', 'cars', 'racs', 'scar', 'four', 'for', 'potatoes', 'creams', 'scream', 'scream']
puts combine_anagrams(words).inspect

# Test a repeated single-character inputs with some captal letters
words = ['a', 'a', 'a', 'A', 'b', 'b', 'c', 'D', 'd']
puts combine_anagrams(words).inspect
