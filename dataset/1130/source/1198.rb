#!/usr/bin/ruby -w
#
def combine_anagrams(words)
	a = []
	words.each { |w| 
		wd = w.downcase.scan(/./).sort
		found = false
		a.each { |ai|
			puts wd
			puts ai[0]
			puts "****"
			#puts ai
			if wd == ai[0].downcase.scan(/./).sort
				#puts ai[0]
				ai.push w
				found = true
			end
		}
		if !found
			a.push [w]
		end
	}
	return a
end


