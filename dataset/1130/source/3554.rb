#Caleb Tomlinson
#SaaS Engineering through coursera.com
#HW 1 Part 3
#2012-05-29

def combine_anagrams(words)
	hashes = Hash.new(0)
	words.each do |x|
		alpha_word = x.downcase.chars.sort.join

		if hashes[alpha_word] == 0
			hashes[alpha_word] = [x]
		else
			hashes[alpha_word] = hashes[alpha_word] << x
		end
	end
	outArray = Array.new
	hashes.each do |key, value|
		outArray << value
	end
	
	return outArray
end


words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

answer = combine_anagrams(words)
answer.inspect

