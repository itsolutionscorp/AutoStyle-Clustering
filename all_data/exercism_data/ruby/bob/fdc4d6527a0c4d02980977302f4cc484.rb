class Bob
	def hey saytobob
		if (saytobob.downcase != saytobob.upcase) && (saytobob == saytobob.upcase)
			"Whoa, chill out!"
		elsif saytobob.strip == ""
			"Fine. Be that way!"
		elsif saytobob[-1] == "?"
			"Sure."
		else "Whatever."
		end
	end
end
