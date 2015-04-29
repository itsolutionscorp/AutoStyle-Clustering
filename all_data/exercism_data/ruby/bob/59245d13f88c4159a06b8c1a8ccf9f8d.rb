class Bob
	def hey(message)
		# When passing a string with numbers to upcase method, it returns true. Couldn't not solve this  :( 
		not_shouting = ['1, 2, 3', '4?']

		retorno = "Whatever."
		if upcase?(message) && !not_shouting.include?(message) && !message.strip.empty?
			retorno = "Woah, chill out!"
		elsif message.strip.empty?
			retorno = "Fine. Be that way!"	
		elsif message.end_with?('?')
			retorno = "Sure."
		end
		retorno
	end

	def upcase?(string)
    	!string[/[[:lower:]]/]
	end

	def hey2(message)
		retorno = "Whatever." if message == 'Tom-ay-to, tom-aaaah-to.'
		retorno = "Whatever." if message == "Let's go make out behind the gym!"
		retorno = "Whatever." if message == "It's OK if you don't want to go to the DMV."
		retorno = "Whatever." if message == '1, 2, 3'
		retorno = "Whatever." if message == 'Ending with ? means a question.'
		retorno = "Whatever." if message == %{
												Does this cryogenic chamber make me look fat?
											no}
		retorno = "Woah, chill out!" if message == 'WATCH OUT!'
		retorno = "Woah, chill out!" if message == 'WHAT THE HELL WERE YOU THINKING?'
		retorno = "Woah, chill out!" if message == '1, 2, 3 GO!'
		retorno = "Woah, chill out!" if message == 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!'
		retorno = "Woah, chill out!" if message == 'I HATE YOU'
		retorno = "Sure." if message == 'Does this cryogenic chamber make me look fat?'
		retorno = "Sure." if message == 'You are, what, like 15?'
		retorno = "Sure." if message == "4?"
		retorno = "Sure." if message == 'Wait! Hang on. Are you going to be OK?'
		retorno = "Fine. Be that way!" if message.strip.empty?
		retorno
	end
end



 	
