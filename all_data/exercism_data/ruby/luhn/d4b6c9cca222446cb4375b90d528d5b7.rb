class Luhn
	def initialize(number)
		@number = number
	end

	def addends
		s_addend = []
		s_number = @number.to_s
		s_number.reverse!
		
		s_number.split("").each_index do |index|
			if index % 2 == 1
        if s_number[index].to_i < 5
          s_addend << (s_number[index].to_i * 2)
        else
      	  s_addend << (s_number[index].to_i * 2 - 9)
        end
      else
      	 s_addend <<  s_number[index].to_i
		  end
	  end

		return s_addend.reverse
	end

	def checksum

		addend = addends
		sum = 0
		addends.each do |addend|
			sum = sum + addend
		end

		return sum
	end

	def valid?
		if checksum % 10 == 0
			return true
		end
		false
	end

	def self.create(number)
    n = Luhn.new(number*10)
    
		unless n.valid?
			puts n.checksum
			to_add =  10 - (n.checksum)%10 
			number = number*10 + to_add
			return number
		
		end
		return number*10
	end
end
