class Bob

	def hey(saying)
		case
		when saying.to_s == ""
			"Fine. Be that way."
		when saying == saying.upcase
			"Woah, chill out!"
		when saying.end_with?("?")
			"Sure."
		else
			"Whatever."
		end

	end
end
