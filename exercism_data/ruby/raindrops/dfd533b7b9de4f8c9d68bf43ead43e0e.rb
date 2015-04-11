class Raindrops
	
	RAINDROP_MAPPING = {
		3 => 'Pling',
		5 => 'Plang',
		7 => 'Plong'
	}
	
	def self.convert(number)

		
		drops = String.new	

		RAINDROP_MAPPING.map{|n,d|
			if number % n == 0
				drops += d
			end
		}

		if drops.empty?
			number.to_s
		else
			drops
		end
	end
end

puts Raindrops.convert(28)
puts Raindrops.convert(1755)
puts Raindrops.convert(34)
