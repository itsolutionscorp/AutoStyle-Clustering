class Raindrops
	Sounds = {3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
	def self.convert(num)
		result = ''	
		Sounds.each do |prime, sound|
			result += sound if num % prime == 0
		end	
		result.length > 1 ? result : num.to_s 	
	end
end
