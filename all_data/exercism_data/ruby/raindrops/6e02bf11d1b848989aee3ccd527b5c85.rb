class Raindrops

	@factorTranslation = {3=>'Pling',5=>'Plang',7=>'Plong'}

	def self.convert(number)
		@containsPrimeFactor = false
		output = ''
		@factorTranslation.each do |divisor,word|
			if number % divisor == 0
				output += word
				@containsPrimeFactor = true
			end
		end
		if @containsPrimeFactor == false
			output = number.to_s
		end
		return output
	end

end
