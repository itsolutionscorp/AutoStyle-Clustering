class Bob
	def hey(text)
		case 
	 	when text.strip.empty?
		 	"Fine. Be that way!"
		when text =~ /[A-Z]/ && text !~ /[a-z]/
			"Whoa, chill out!"
		when text.end_with?('?')
			"Sure."
		else 
			"Whatever."
		end
	end
end
