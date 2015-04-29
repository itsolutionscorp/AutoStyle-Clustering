class Raindrops
	@@prime_names = {
		3 => "Pling",
		5 => "Plang",
		7 => "Plong"
	}

	def self.convert(num)
		output = ""
		@@prime_names.each do |prime, name|
			output += name if num % prime == 0
		end
		output.empty? ? num.to_s : output
	end
end
