class Raindrops
	def self.convert(n)
		drops = ""
		drops += n % 3 == 0 ? "Pling" : ""
		drops += n % 5 == 0 ? "Plang" : ""
		drops += n % 7 == 0 ? "Plong" : ""

		drops == "" ? n.to_s : drops
	end
end
