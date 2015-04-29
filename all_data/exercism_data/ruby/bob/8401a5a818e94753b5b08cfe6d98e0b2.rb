class Bob
	def hey(msg)
		if (msg.match(/^[\s]*$/) && !msg.match(/\n/))
			"Fine. Be that way!"
		elsif (msg == msg.upcase && msg.match(/[^0-9?,\s]\w*/)) 
			"Woah, chill out!" 
		elsif (msg.match(/\?$/) && !msg.match(/\n/))  
			"Sure."
		else
			"Whatever."
		end
	end
end
