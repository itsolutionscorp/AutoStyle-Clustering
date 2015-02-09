#  => output:  [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]

# HINT: you can quickly tell if two words are anagrams by sorting their
#  letters, keeping in mind that upper vs lowercase doesn't matter

def combine_anagrams(words)
	
word1_id=0
output = Array.new()
checked = Array.new()
words.each{|word1|
	if !checked.include?(word1)
	group_output = Array.new(0)
	group_output.push(word1)
	word2_id=0
	words.each{|word2|
		if check_words(word1,word2) && word1_id!=word2_id
			group_output.push(word2)
			checked.push(word2)
		end
		word2_id +=1
	}
	output.push(group_output)
	end
	word1_id+=1
}
puts output.inspect

return output
end

def check_words(word1, word2)

	if word1.downcase.scan(/./).sort.join==word2.downcase.scan(/./).sort.join
		return true
	else
		return false
	end
end

words = ['cars','cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'] 

combine_anagrams(words)
