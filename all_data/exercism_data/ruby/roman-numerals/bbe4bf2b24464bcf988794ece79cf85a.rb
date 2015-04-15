class Integer
	def to_roman
		roman = ""
		thousands = self / 1000
		hundreds = (self - thousands*1000) / 100
		tens = (self - thousands*1000 - hundreds*100) / 10
		ones = (self - thousands*1000 - hundreds*100- tens*10)

		romanNumerals = {
			1 => :I, 5 => :V, 10 => :X, 50 => :L, 100 => :C, 500 => :D, 1000 => :M
		}
		#Get the thousands
		while thousands > 0 do
			roman += romanNumerals[1000].to_s
			thousands -= 1
		end

		roman += calcRomanNumerals(hundreds, romanNumerals[100], romanNumerals[500], romanNumerals[1000])
		roman += calcRomanNumerals(tens, romanNumerals[10], romanNumerals[50], romanNumerals[100])
		roman += calcRomanNumerals(ones, romanNumerals[1], romanNumerals[5], romanNumerals[10])
		
		return roman
	end

	def calcRomanNumerals (digit, tenth, half, whole)
		roman = ""

		if (digit == 9) #IX
			roman += tenth.to_s
			roman += whole.to_s
		elsif (digit >= 5) #V..VIII
			roman += half.to_s
			digit -= 1
			while digit >= 5 do
				roman += tenth.to_s
				digit -= 1
			end
		elsif (digit == 4) #IV
			roman += tenth.to_s
			roman += half.to_s
		else #I...III
			while digit > 0 do
				roman += tenth.to_s
				digit -= 1
			end
		end

		return roman
	end	
end
