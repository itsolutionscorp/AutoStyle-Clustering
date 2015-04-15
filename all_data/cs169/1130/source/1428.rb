class NotAnArray < StandardError
end

def combine_anagrams(words)
	raise NotAnArray unless words.is_a?(Array)
	ana_hash = Hash.new
	words.each do |word|
		key=word.downcase.split("").sort.join("")
		if(ana_hash[key].nil?)
			ana_hash[key] = [ word ]
		else
			ana_hash[key] << word
		end
	end
	ana_grp_list = Array.new
	ana_hash.each_value do |value|
		ana_grp_list << value
	end
	ana_grp_list
end
