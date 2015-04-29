class Raindrops

	def self.convert(num)
		sound = ""
		sound << "Pling" if num % 3 == 0
		sound << "Plang" if num % 5 == 0
		sound << "Plong" if num % 7 == 0
		return num.to_s if sound.length == 0
		return sound
	end
end
