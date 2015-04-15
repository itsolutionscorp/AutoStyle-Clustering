class Raindrops
	def self.convert(number)
		out = ''
		{3 => "Pling", 5 => "Plang", 7 => "Plong"}.each do |factor, text|
			if number % factor == 0
				out << text
			end
		end
		if out == ''
			number.to_s
		else
			out
		end
	end
end
