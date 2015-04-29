class Fixnum
   def is_divisible_by(number)
      return self % number == 0
   end
end

class Raindrops
	def self.convert (number)
		sound = ""		
		sound += "Pling" if number.is_divisible_by(3)
		sound += "Plang" if number.is_divisible_by(5)
		sound += "Plong" if number.is_divisible_by(7)
			
		return number.to_s if sound.empty? else sound
	end
end
