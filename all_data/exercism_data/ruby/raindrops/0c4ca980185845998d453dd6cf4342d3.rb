class Raindrops
	@primeHash = { 3.0 => "Pling", 5.0 => "Plang", 7.0 =>"Plong" }
	def self.convert(input)
		
		retString = ""
		@primeHash.each do | key, value |
			retString += value.to_s if (input / key)  % 1 == 0
		end

		if retString == ""
			input.to_s
		else
			retString
		end
	end

	
end
