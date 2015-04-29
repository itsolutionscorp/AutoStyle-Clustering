
def combine_anagrams(words)	
	# <YOUR CODE HERE>
	
	h = Hash.new()	
	words.length.times do |i|
		v = 0
		
		w = words[i].split('').sort!.join('')
		
		w.length.times do |j|
			
			v += w.clone.slice!(j).downcase.ord			
		end		
		
		
		if h.key?(v) then			
		
			h[v] << words[i]
		else			
			h[v] = Array.new()
			h[v] << words[i]
		end	
		
	end	
	
	return h.values
end
