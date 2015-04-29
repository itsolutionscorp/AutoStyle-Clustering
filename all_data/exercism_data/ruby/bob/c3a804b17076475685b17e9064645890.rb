class Bob
	def hey(s)
		s.strip!
		case
			when s.empty? then "Fine. Be that way!"
			when s.upcase == s then "Woah, chill out!"
			when s.match(/[?]\Z/) then "Sure."
			else "Whatever."
		end
	end
end
