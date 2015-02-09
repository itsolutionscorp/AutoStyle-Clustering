def combine_anagrams(words)
   anagrams = Hash.new
   words.each do |word|
       key = word.downcase.split('').sort.join
       if !anagrams[key] then anagrams[key] = Array.new end
       anagrams[key] << word
   end
   results = Array.new
   anagrams.each do |k,v| results << v end
   results
end

=begin
print combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
=end
