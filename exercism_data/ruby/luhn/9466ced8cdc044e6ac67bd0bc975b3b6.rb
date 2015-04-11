class Luhn

	attr_reader :luhn
	
	def initialize(luhn)
		@luhn = luhn
	end
	
	def addends
		nn = []
		luhn.to_s.reverse.split(//).each_with_index do |n,i|
			if i % 2 != 0 
				nn << 	if n.to_i * 2 >= 10 
								then n.to_i * 2 - 9
								else n.to_i * 2
								end 
			else 
				nn << n.to_i
			end
		end
		nn.reverse
	end
	
	def checksum
		addends.inject(:+)
	end
	
	def valid?
		checksum % 10 == 0
	end
	
	def self.create(to_luhn)
		l = Luhn.new(to_luhn * 10)
		check_digit = (10 - l.checksum % 10) % 10
		l.luhn + check_digit
	end

end
