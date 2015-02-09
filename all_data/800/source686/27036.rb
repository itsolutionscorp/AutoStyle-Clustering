def combine_anagrams(words)
	output=Array.new()
	words.each do |w|
		output+=[words.select { |w1| w1.downcase.chars.sort.join==w.downcase.chars.sort.join}]
	end
	output=output.uniq
	puts "Combining Anagrams"
	p output #printing output as it is
end
combine_anagrams(['cars','for','potatoes','Racs','Four','Scar','creams','Scream'])
combine_anagrams(['Tame','mate','team','meat','Rails','liars','sailr'])
