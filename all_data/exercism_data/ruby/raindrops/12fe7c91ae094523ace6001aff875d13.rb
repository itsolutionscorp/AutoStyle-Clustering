class Raindrops

	def self.convert(num)
		@num = num
		sound = ""	
		if @num%3 == 0
			@num = @num / 3
			sound << "Pling"
			Raindrops.convert(@num)
		end
		if @num%5 == 0
			@num = @num / 5
			sound << "Plang"
			Raindrops.convert(@num)
		end
		if @num%7 == 0
			@num = @num / 7
			sound << "Plong"
			Raindrops.convert(@num)
		end		
		if sound.length == 0
			return num.to_s
		end
		return sound
	end
end
