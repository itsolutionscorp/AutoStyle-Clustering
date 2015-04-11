class Bob
	def hey(greeting)
		if greeting.upcase == greeting && greeting.match(/[A-z]/)
			"Woah, chill out!"
		elsif greeting.match(/\?$/) && !greeting.match(/\n/)
			"Sure."
		elsif greeting.match(/\A +/) or greeting == ""
			"Fine. Be that way!"
		else
			'Whatever.'
		end
	end
end
