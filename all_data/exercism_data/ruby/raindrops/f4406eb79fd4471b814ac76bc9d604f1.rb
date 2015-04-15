class Raindrops
	def self.convert(num)
		output = ""
		if num % 3 == 0 
			output << "Pling"
		end
		if num % 5 == 0
			output << "Plang"
		end
		if num % 7 == 0
			output << "Plong"
		end
		output << num.to_s if output.eql?("")
		output
	end
end
