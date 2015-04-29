def combine_anagrams(words)
 h = Hash.new
 result = Array.new
 words.each {|word| 
 	letters = word.downcase.chars.sort.join
 	if h.has_key?(letters)
 		h.store(letters, h.fetch(letters).push(word))
 	else
 		h.store(letters, Array.new(1,word))
 	end
 }
 h.each_value{|value| result.push(value)}
 return result
end