
def combine_anagrams(words)
	new_words = Array.new
	result = Array.new
	
	words.each  {|w| new_words << w.downcase.chars.sort.join }
	
	
	#w.chars.sort { |a, b| a.casecmp(b) } .join
	
	
	
	
	

	a = new_words.uniq
	
	a.each do |k|
	
	b = new_words.map {|it| k == it}
	
	

	
	res = Array.new
	b.length.times do |m|
		
		if b[m] == true 
		
		res << words[m]
		
		end
	end
	
	result << res
	
	end
	
	return result
	
end

puts "#{combine_anagrams(['Cars', 'fOr', 'potAtoEs', 'racs', 'four','scar', 'creaMs', 'Scream'] )}"
