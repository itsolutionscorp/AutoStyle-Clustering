=begin
HW1 Part1
	anagrams
=end

def combine_anagrams(words)
	hash_ana = Hash.new([])
	words.each do |w| hash_ana[w.downcase.split(//).sort] += [w]
	end
	
	arr_ana = []

	hash_ana.each_pair do |k,v|
		arr_ana += [v]
	end
	
	arr_ana
end

combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])