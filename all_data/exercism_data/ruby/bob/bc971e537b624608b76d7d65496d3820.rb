=begin
Exercise: Bob
---------------------------------------
Bob is a lackadaisical teenager. In conversation, his responses are very limited.
Bob answers 'Sure.' if you ask him a question.
He answers 'Woah, chill out!' if you yell at him (ALL CAPS).
He says 'Fine. Be that way!' if you address him without actually saying anything.
He answers 'Whatever.' to anything else.
_______________________________________
=end

class Bob
	def hey(string)
		string ||= nil
		string = Response.new(string)
		case
			when string.inquire?
				'Sure.'
			when string.caps? # doesn't like: string.upcase 
				'Woah, chill out!'
			when string.empty?
				"Fine. Be that way!"
			when string.rude?
				"Whatever."
			else
				"Whatever."
		end
	end

	private
	class Response < String
		def caps?
			eql?(upcase)
		end
		# unused
		def upcase?
			length.upcase
		end
		def inquire?
			end_with? "?"
		end
		def rude?
			end_with? "!"
		end
	end
end

=begin
Bob.hey('Tom-ay-to, tom-aaaah-to.');
Bob.hey('WATCH OUT!');
Bob.hey('Does this cryogenic chamber make me look fat?');
Bob.hey('You are, what, like 15?');
Bob.hey("Let's go make out behind the gym!");
Bob.hey("It's OK if you don't want to go to the DMV.");
Bob.hey('WHAT THE HELL WERE YOU THINKING?');
Bob.hey('1, 2, 3 GO!');
Bob.hey('1, 2, 3');
Bob.hey('4?');
Bob.hey('ZOMG THE %^*@#$(*^ ZOMBIES ARE COMING!!11!!1!');
Bob.hey('I HATE YOU');
Bob.hey('Ending with ? means a question.');
Bob.hey("Wait! Hang on. Are you going to be OK?");
Bob.hey('');
Bob.hey('    ');
=end
