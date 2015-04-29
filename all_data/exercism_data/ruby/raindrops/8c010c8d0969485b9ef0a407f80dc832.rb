class Raindrops
	@primeHash = { 3.0 => "Pling", 5.0 => "Plang", 7.0 =>"Plong" }
	def self.convert(input)
		primefactors = Array.new()

		@primeHash.each do | key, value |
			if (input / key)  % 1 == 0
				#tempVal is a factor of the input
				primefactors.push(value)
			end
		end

		printValues(primefactors, input)
	end

	def self.printValues(primefactors, input)
		if primefactors.empty?
			input.to_s
		else
			retString = ""

			primefactors.uniq.each do | factor |
				retString += factor.to_s
			end

			retString
		end
	end
end
