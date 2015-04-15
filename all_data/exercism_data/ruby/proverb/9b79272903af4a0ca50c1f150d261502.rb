class Proverb
	
	def initialize(*chain)
		if chain.count < 2 
			'A minimum of two arguments required.'
		else
			@proverb = ""	
			@qualifier = ''			
			chain.each_with_index do |word, index|
				if chain.last.is_a?(Hash) == true
					@qualifier = chain.last[:qualifier].to_s + ' '
					chain.pop 
				end
					
				@proverb += "For want of a #{chain[index]} " 
				@proverb += "the #{chain[index + 1]} was lost.\n" 				
				break unless chain[index + 2]
			end
		@proverb += "And all for the want of a #{@qualifier}#{chain[0]}."
		end
	end
	

	def to_s
		@proverb
	end
	
end
