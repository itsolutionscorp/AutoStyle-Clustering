class Raindrops
	def self.convert(num)
		noise = ""
		if num%3 == 0
			noise = noise + 'Pling'
		end
		if num%5 == 0
			noise = noise + 'Plang'
		end
		if num%7 == 0
			noise = noise + 'Plong'
		end
		if num%3!=0 && num%5!=0 && num%7 !=0
			noise = num
		end
		noise.to_s
	end
end
