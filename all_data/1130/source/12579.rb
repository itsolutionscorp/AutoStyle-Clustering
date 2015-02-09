def combine_anagrams(words)
   if words.length == 0
      return words
   end
   result = []
   anagrams = [] 	
   if words.length == 1
      result.push(words)
      return result 
   end
   key = words.shift
   anagrams.push(key)
   copy = Array.new(words)
   copy.each do |w|
      if key.downcase.scan(/./).sort == w.downcase.scan(/./).sort
	 anagrams.push(w)
	 words.delete(w)
      end
   end
   result.push(anagrams.sort)
   if words.length > 0
      result.concat(combine_anagrams(words)) 
   end
   return result.sort
end

input = ['b', 'c', 'a', 'b', 'A', 'a', 'b', 'a', 'd', 'D']
output = combine_anagrams(input)
print output

