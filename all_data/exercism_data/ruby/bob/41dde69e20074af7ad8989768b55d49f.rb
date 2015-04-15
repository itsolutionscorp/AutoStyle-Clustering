class Bob 
		def hey(statement)
		@statement = statement
			if yelling
				"Woah, chill out!"
			elsif asking
				"Sure."
			elsif silent
				"Fine. Be that way!"
			else
				"Whatever."
			end
		end

private 

	def yelling
		statement.match(/[a-zA-Z]/) && statement == statement.upcase ? true : false
	end

	def asking 
		statement[-1] == "?" ? true : false
	end

	def silent
		statement.strip.empty? ? true : false
	end

	def statement
		@statement
	end
end
