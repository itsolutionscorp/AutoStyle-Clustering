class Hamming
	attr_reader :first, :second
	def initialize(first, second)
		@first = first.chars
		@second = second.chars
	end
	
	def calculate 
		if first.length < second.length
			shorter, longer = first, second
		else
			shorter, longer = second, first
		end
		puts shorter.zip(longer).count{|pair| pair[0] != pair[1]}
	end

end
