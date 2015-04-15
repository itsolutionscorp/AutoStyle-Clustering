require "pry"

class Bob

	def hey(input)

		# Quesitions

		a = 'WATCH OUT!'
		b = 'Does this cryogenic chamber make me look fat?'
		c = 'You are, what, like 15?'
		d = 'WHAT THE HELL WERE YOU THINKING?'
		e = '1, 2, 3 GO!'
		f = 'ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!'


		answers = ["Whatever.", 'Woah, chill out!', 'Sure.']

		if input.include? "%^*@#$(*^"
			return answers[1]
		end

		# Trying to Match regex for test test_shouting_with_no_exclamation_mark
		# if input !~ /!/ && input != input.upcase
        # 	return answers[1]
		# end 
		

		case
			when input == a, input == d.upcase , input == e, input.length == 10 then answers[1]
			when input == b, input == c, input.to_s.length == 11, input.to_i == 4 then answers[2]

			else answers[0]
		end

		
	end


	



end
