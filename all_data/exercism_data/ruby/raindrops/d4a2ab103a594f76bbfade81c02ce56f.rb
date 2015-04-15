class Raindrops
	def self.convert(num)
		drops = ''
		drops += 'Pling' if num % 3 == 0
		drops += 'Plang' if num % 5 == 0
		drops += 'Plong' if num % 7 == 0
		unless drops.empty?
		    drops
		else
			num.to_s
		end 
	end
end
