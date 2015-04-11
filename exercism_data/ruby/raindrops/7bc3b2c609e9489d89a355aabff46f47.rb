class Raindrops
	def self.convert (number)
		sound = ""		
		sound += "Pling" if number % 3 == 0
		sound += "Plang" if number % 5 == 0
		sound += "Plong" if number % 7 == 0
			
		return number.to_s if sound == "" else return sound
	end
end
