class Raindrops
	PLINGS = {3 => "Pling", 5 => "Plang", 7 => "Plong"}
	def self.convert(num)
		num.to_s
		output = ""
		PLINGS.each do |div, str|
			output += str if num % div == 0
		end
		output.empty? ? num.to_s : output
	end
end
