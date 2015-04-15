# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
  wordss = words.map do |word| word.downcase.scan(/./).sort.join end.uniq
  result = Array.new
  wordss.map do |word_u|
  		result1 = Array.new
		words.map do |word|
			if word_u == word.downcase.scan(/./).sort.join
				result1 << word
			end
		end
		result << result1
  end
  return result
end

#puts combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])