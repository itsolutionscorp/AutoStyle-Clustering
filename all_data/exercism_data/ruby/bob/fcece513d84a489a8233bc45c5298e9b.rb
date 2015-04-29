class Bob
	def hey(msg)
		case
			when (msg.nil? or msg.empty?)				then "Fine. Be that way!"
			when (msg =~ /^[A-Z\d\W]+[!\?]*$/)	then "Woah, chill out!"
			when (msg.end_with? "?")						then "Sure."
			else "Whatever."
		end
	end
end
