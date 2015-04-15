
# input: ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
# => output: [["cars", "racs", "scar"], ["four"], ["for"], ["potatoes"], ["creams", "scream"]]
# HINT: you can quickly tell if two words are anagrams by sorting their
# letters, keeping in mind that upper vs lowercase doesn't matter


def combine_anagrams(words)
	sorted = Hash.new
	k=0
	words.each { |c| sorted[k]=c.downcase.chars.sort.join; k=k+1  }
	anagram = Array.new
	a = Array.new
	b = Array.new
	i=0
	sorted.invert.each { |w,y| a[i] = sorted.select { |k,v| v == w  }.keys; a[i].each { |x| b << words[x] }; anagram << anagram[i].to_a + b.to_a ; b.clear; i=i+1 }
	return anagram
end


