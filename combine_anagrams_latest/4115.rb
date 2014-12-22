def combine_anagrams(words)
	words_final = []
	words_control = []
	words.each{|x| words_control << x}
	words.each do |x|
		if words_control.include?(x)
			temp = [x]
			words_control.delete(x)
			words_control.each do |c|
				if c.split(%r/\B/i).each{|z| z.downcase!}.sort == x.split(%r/\B/i).each{|z| z.downcase!}.sort
					temp << c
					words_control.delete(c)
				end
			end
		end
		words_final << temp
	end
	return words_final.compact
end