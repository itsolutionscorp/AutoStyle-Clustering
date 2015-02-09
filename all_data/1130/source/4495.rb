def combine_anagrams(words)
	output = [];
	words.each do |w|
		processed = false;
		output.each_index do |x|
			if(not processed and output[x][0].downcase.scan(/./).sort.join == w.downcase.scan(/./).sort.join)
				processed = true;
				output[x].push(w);
			end
		end
		unless processed
			output.push([w]);
		end
	end
	output
end

#combine_anagrams(['cars', 'for', 'potatoes', 'racs', 'four','scar', 'creams', 'scream'])
