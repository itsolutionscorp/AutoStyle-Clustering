class Raindrops
	def self.convert(input)
		if input.is_a? Integer
			factors = []
			number = input

			while(number%2==0) do
				factors.push(2)
				number = number/2
			end
			
			(3..Math.sqrt(number)).step(2).each do |i|
	      while (number%i == 0) do
	        factors.push(i.to_i)
	        number = number/i;
	      end
	    end

	    if number > 2
	    	factors.push(number.to_i)
	    end

	    factors = factors & [3,5,7]
	    unless factors.empty?
	    	string = ""
		    factors.each do |factor|
					case factor
					when 3
						string += "Pling"
					when 5
						string += "Plang"
					else
						string += "Plong"
					end
				end
				return string
			else
				return input.to_s
			end
		else
			puts "Please provide a valid number"
		end
	end
end
