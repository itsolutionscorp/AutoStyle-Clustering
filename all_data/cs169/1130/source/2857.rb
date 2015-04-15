def combine_anagrams(words)
	output={}
	outarray=[]
	words.each do |word|
		cond=word.downcase.split(//).sort.join
		if output.has_key?(cond)
			output[cond]=output[cond]<<word
		else
			output[cond]=[]<<word
		end
	end
	output.keys.each {|s| outarray<<output[s].sort}
	outarray
	
end
#['cars', 'fOr', 'Potatoes', 'racs', 'racs', 'RACs', 'four','scar', 'creams', 'scream']

["potS", "STOPS", "spots", "spot", "stop", "tOps", "tops",  "sAUsage"]