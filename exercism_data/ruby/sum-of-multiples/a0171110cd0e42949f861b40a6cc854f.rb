class SumOfMultiples
	class Summatory
		def initialize(*selectors)
			@selectors = *selectors
		end
		
		def to(limit)
			(1...limit).to_a.select { |n| selected?(n) }.reduce(:+) || 0
		end
		
	private
	
		def selected?(num)
			@selectors.inject(false) do |selected, selector|
				selected || (num.modulo(selector) == 0)
			end
		end
	end
	
	DEFAULT_SELECTORS = [3, 5]
	
	def self.to(limit)
		Summatory.new(*DEFAULT_SELECTORS).to(limit)
	end
	
	def initialize(*numbers)
		@numbers = *numbers
	end
	
	def to(limit)
		Summatory.new(*@numbers).to(limit)
	end
end
