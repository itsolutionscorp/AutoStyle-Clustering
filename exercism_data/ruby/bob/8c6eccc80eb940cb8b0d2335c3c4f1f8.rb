class Bob
	def hey(blather = nil)
		puts(blather)
		if blather.nil?
			return "Fine. Be that way!"
		elsif /^[[:space:]]*$/.match(blather)
			return "Fine. Be that way!"
		# Match shouting, text with no lower case letters.
		elsif /^[^a-z]+$/.match(blather)
			return "Woah, chill out!"
		#elsif blather[-1] == '?' 
		elsif /[A-Za-z0-9]\?/.match(blather)
			return "Sure."
		else
			return "Whatever."
		end
	end
end

if __FILE__ == $0
	kid = Bob.new
	puts(kid.hey)
	puts(kid.hey(nil))
	puts(kid.hey('  '))
	puts(kid.hey("Sup?"))
	puts(kid.hey("SUP?"))
	puts(kid.hey("SUP 123?"))
	puts(kid.hey('SUP ^$#\%^#$\%#$ 123?'))
	puts(kid.hey("Nice haircut."))
end
