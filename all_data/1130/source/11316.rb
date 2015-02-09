# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams',
#'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"],
#["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter


#answer 3, but i have idea of this (reused)
# a=['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams','scream']
# x=Array.new
# a.each{|f| x<<f.split("").sort.join("")}
# count_words x.join(" ")
# the rest is search :)

def combine_anagrams(words)
# <YOUR CODE HERE>
temporal = Array.new
resultado = Array.new
words.length.times do |x|
	words.length.times do |v|
		if words[x].split(//).sort.to_s.casecmp(words[v].split(//).sort.to_s) == 0
			temporal << words[v]
		end
	end
		resultado << temporal
		temporal = Array.new
	end
x = 0
resultado_final = Array.new
until resultado.length == 0
	resultado_final << resultado[0]
	resultado.delete(resultado[0])
	x = x + 1
end

resultado_final
end