  
def combine_anagrams(words)
	#anagrams = ( words.map {|x| x.downcase.scan(/./).sort.join} ).uniq
	
	anagrams =  words.map {|x| x.downcase.split(//).sort.join}
	uniq_anagrams = anagrams.uniq
		
	combined_anagrams = []
	
	for word in uniq_anagrams
		combined_anagrams << words.select { |w| w.downcase.split(//).sort.join == word}
	end
	combined_anagrams
end


puts "input\n"
print words = ['Cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']
puts "\nAnagram is\n"
print combine_anagrams(words)