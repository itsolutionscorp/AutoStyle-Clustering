class Bob
	def hey (sup)
		if sup =~ /\A\s*\Z/
			'Fine. Be that way!'
		elsif sup == sup.upcase
			'Woah, chill out!'
		elsif sup =~ /\?\Z/
			'Sure.'
		else
			'Whatever.'
		end
	end
end
