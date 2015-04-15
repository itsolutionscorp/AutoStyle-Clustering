class Raindrops
	def self.convert(input)
		drop = ""
		drop += "Pling" if (input % 3 == 0)
		drop += "Plang" if (input % 5 == 0)
		drop += "Plong" if (input % 7 == 0)
		if drop == ""			
			drop = input.to_s
		end
		drop
	end
end
