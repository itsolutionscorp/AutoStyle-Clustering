class Bob
	def hey(x)
		if x == x.upcase && x.match(/[a-zA-Z]/);"Woah, chill out!"
		elsif x[-1] == '?';"Sure."
		elsif x.strip == '';'Fine. Be that way!'
		else;"Whatever."
		end
	end
end
