class Raindrops
	def convert(num)
		if (num%3) == 0
			if (num%5) == 0
				if (num%7) == 0
					"PlingPlangPlong"
				else
					"PlingPlang"					
				end
			elsif(num%7) == 0
				"PlingPlong"
			else
				"Pling"
			end
		elsif (num%5) == 0
			if (num%7) == 0
				"PlangPlong"
			else
				"Plang"
			end
		elsif (num%7) == 0
			"Plong"
		else
			num.to_s
		end
	end
end
