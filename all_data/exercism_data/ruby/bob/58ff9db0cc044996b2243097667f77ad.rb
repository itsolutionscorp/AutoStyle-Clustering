class Bob
	def hey(msg)
		if /^[A-Z]+$/.match msg.gsub(/[^a-zA-Z]/, '')
			"Woah, chill out!"
		elsif msg[-1] == "?"
			"Sure."
		elsif msg.strip.empty?
			"Fine. Be that way!"
		else
			"Whatever."
		end	
	end
end
