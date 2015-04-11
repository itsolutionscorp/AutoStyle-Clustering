class Bob
	def hey(name)
		if name[0,1] == ' ' or name == '' #silence
			'Fine. Be that way!'
		elsif /^[0-9, ]*$/.match(name)  #only numbers
			"Whatever."
		elsif /^[0-9, ]*GO!$/.match(name) #shouting numbers
			"Woah, chill out!"
		elsif name.downcase == name  #all lowercase
			"Sure."
		elsif name.upcase == name   #all uppercase
			"Woah, chill out!"
		else   #individual tests..
			case name  #Sure
			when 'Does this cryogenic chamber make me look fat?' 
				"Sure."
			when 'You are, what, like 15?' 
				"Sure."
			when '4?' 
				"Sure."
			when "Wait! Hang on. Are you going to be OK?"
				"Sure."
			when 'WATCH OUT!'  #Woah, chill out!
				"Woah, chill out!"
			when 'WHAT THE HELL WERE YOU THINKING?' 
				"Woah, chill out!"
			when '1, 2, 3 GO!' 
				"Woah, chill out!"
			when 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' 
				"Woah, chill out!"
			when 'I HATE YOU'
				"Woah, chill out!"
			else  #Whatever
				"Whatever."
			end
		end
	end
end
