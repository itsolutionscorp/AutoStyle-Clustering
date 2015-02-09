#! /usr/bin/env ruby

def combine_anagrams(words)
	output = []
	words.each do |word|
		word_temp = word
		output.each_index do |i|
			if output[i][0].to_s.downcase.split(//).sort.join ==
				word_temp.downcase.split(//).sort.join
				output[i] += [word_temp]
				word_temp = []
				break
			end
		end
		output += [[word_temp]] unless word_temp == []
  	end
	return output
end

