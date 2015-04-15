class Raindrops
	def self.convert(number)
		{3 => "Pling", 5 => "Plang", 7 => "Plong"}.reduce([]) do |acc, (index, value)|
			if (number % index == 0)
				acc << value
			elsif ((acc.length == 0) && (index == 7))
				acc << number
			end
			acc
		end.join("")
	end
end
