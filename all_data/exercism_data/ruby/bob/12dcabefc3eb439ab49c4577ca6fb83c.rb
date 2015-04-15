
class Bob
	def hey (frace)
		if frace.strip.empty?
			"Fine. Be that way!"
		
		elsif frace == frace.upcase and /[A-Z]/.match(frace)
			"Woah, chill out!"
		elsif frace.end_with?('?') 
			"Sure."
		else
		"Whatever."

		end
	end
end


