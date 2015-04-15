class Raindrops

	def convert(int)
		sound = prime3(int) + prime5(int) + prime7(int)
		sound.empty? ? int.to_s : sound 
	end

	def prime3(int)
		int % 3 == 0 ? "Pling" : ""	
	end

	def prime5(int)
		int % 5 == 0 ? "Plang" : ""
	end

	def prime7(int)
		int % 7 == 0 ? "Plong" : ""
	end
end
