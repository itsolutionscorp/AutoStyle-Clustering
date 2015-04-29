class Raindrops
	def self.convert(n)
		raindrops = ""
		raindrops << "Pling" if n % 3 == 0
		raindrops << "Plang" if n % 5 == 0
		raindrops << "Plong" if n % 7 == 0
		raindrops << n.to_s  if raindrops == ""
		raindrops
	end
end
