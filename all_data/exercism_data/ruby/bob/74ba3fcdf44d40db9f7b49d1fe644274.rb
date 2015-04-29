class Bob	
	def hey(say)
		say = say.gsub(/\n/, "").strip
		if say.empty?
			"Fine. Be that way!"
		elsif say.gsub(/[^A-Z]*/, '') == say.gsub(/[^a-z]/i, '') && say.gsub(/[^A-Z]*/, '').empty? == false
			#if all upercase and not empty after removing numbers
			"Whoa, chill out!"
		elsif say.end_with?("?")
			"Sure."
		else
			"Whatever."
		end
	end
end
