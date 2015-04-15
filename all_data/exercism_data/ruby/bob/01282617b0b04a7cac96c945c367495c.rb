class Bob
	def new
	end
	def hey m
		m.strip!
		return 'Fine. Be that way!' if m==nil || m==''
		len=m.length
		for i in 0..(len-1)
			if letter?(m[i])
				return 'Woah, chill out!' if m==m.upcase
			end
		end
		return 'Sure.' if m[len-1]=='?'
		"Whatever."
	end
	def letter?(l)
		l =~ /[[:alpha:]]/
	end	
end
