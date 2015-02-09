#words = ['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream']

def combine_anagrams(words)
	whash = {}
	words.each do |w|
		w1 = w.downcase.split('').sort.join
		if whash.include? w1
			whash[w1].push(w)
		else
			whash[w1] = [w]	
		end
	end

	whash.values
  #   <YOUR CODE HERE>
end