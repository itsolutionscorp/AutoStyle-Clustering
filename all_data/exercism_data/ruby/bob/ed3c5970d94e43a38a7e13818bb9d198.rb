class Bob

	def hey(statement)
		statement = statement.to_s
		statement = statement.strip

		last = statement[-1, 1]
		upcase = statement.upcase

		if statement == ''
			"Fine. Be that way!"
		elsif statement == upcase
			"Woah, chill out!"
		elsif last == "?"
			"Sure."
		else
			"Whatever."
		end
	end

end
