# input:
words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter
def combine_anagrams(words)
	output = Array.new # function result
	words.each do |word|
		sorted = word.upcase.chars.sort.join
		print sorted, ' '
		group = output.find {|g| sorted == g[0].upcase.chars.sort.join}
		if not group
			group = []
			output.push group
		end
		group.push word
	end
	output
end

=begin
p combine_anagrams(words)
ws1 = ["A", "a"]
ws2 = ["HeLLo", "hello"].sort
p combine_anagrams(ws1)
p ws1
p combine_anagrams(ws2)
p ws2
=end