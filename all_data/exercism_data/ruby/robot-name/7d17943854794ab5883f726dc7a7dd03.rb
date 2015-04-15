class Robot
	LETTERS = ('A'..'Z').to_a + ('a'..'z').to_a

	def initialize
		self.reset
	end
	
	def name
		@name
	end
	
	def reset
		@name = "#{name_letters}#{name_numbers}"
	end
	
private
	def name_letters
		2.times.to_a.map { random_letter }.join
	end
	
	def random_letter
		LETTERS.sample
	end
	
	def name_numbers
		Random.rand(1000).to_s.rjust(3, '0')
	end
end
