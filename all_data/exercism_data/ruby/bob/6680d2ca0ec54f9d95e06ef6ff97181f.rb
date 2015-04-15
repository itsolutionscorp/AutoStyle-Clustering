class Bob
	def hey params
		if params.empty?
			"Fine. Be that way!"
		elsif params == params.upcase
			"Woah, chill out!"
		elsif /\?$/.match(params) && !(/\n/.match(params))
			"Sure."
		elsif /\n/.match(params)
			"Whatever."
		else
			"Whatever."
		end
		
	end
end
