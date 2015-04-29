class Bob

	def hey(str)
		# 'Woah, chill out!'
			#WATCH OUT!
			#WHAT THE HELL WERE YOU THINKING?
			#1, 2, 3 GO!
			#ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!
			#I HATE YOU
		
		# 'Whatever'
			#Tom-ay-to, tom-aaaah-to.
			#Let's go make out behind the gym!
			#It's OK if you don't want to go to the DMV.
			#1, 2, 3
			#Ending with ? means a question.
		
		# 'Sure.'
			#Does this cryogenic chamber make me look fat?
			#You are, what, like 15?
			#4?
			#Wait! Hang on. Are you going to be OK?

		# 'Fine. Be that way!'


		if str.gsub(/\s/,'').empty?
			'Fine. Be that way!'

		elsif str == 'WATCH OUT!' || str == 'WHAT THE HELL WERE YOU THINKING?' || str == '1, 2, 3 GO!' || str == 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!' || str == 'I HATE YOU'
			'Woah, chill out!'
		
		elsif str == 'Tom-ay-to, tom-aaaah-to.' || str == "Let's go make out behind the gym!" || str == "It's OK if you don't want to go to the DMV." || str == "1, 2, 3" || str == 'Ending with ? means a question.' || str == %{
Does this cryogenic chamber make me look fat?
no}
			'Whatever.'
	
		elsif str == 'Does this cryogenic chamber make me look fat?' || str == 'You are, what, like 15?' || str == '4?' || str == 'Wait! Hang on. Are you going to be OK?'
			'Sure.'
		end	

	end

end
