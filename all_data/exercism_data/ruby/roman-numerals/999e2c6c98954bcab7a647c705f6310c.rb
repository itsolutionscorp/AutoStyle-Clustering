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

		#Get the hundreds
		if (hundreds == 9) #CM
			roman += romanNumerals[100].to_s
			roman += romanNumerals[1000].to_s
		elsif (hundreds >= 5) #D .. DCCC
			roman += romanNumerals[500].to_s
			hundreds -= 1
			while hundreds >= 5 do
				roman += romanNumerals[100].to_s
				hundreds -= 1
			end
		elsif (hundreds == 4) #CD
			roman += romanNumerals[100].to_s
			roman += romanNumerals[500].to_s
		else #C..CCC
			while hundreds > 0 do
				roman += romanNumerals[100].to_s
				hundreds -= 1
			end
		end

		#Get the tens
		if (tens == 9) #XC
			roman += romanNumerals[10].to_s
			roman += romanNumerals[100].to_s
		elsif (tens >= 5) #L..LXXX
			roman += romanNumerals[50].to_s
			tens -= 1
			while tens >= 5 do
				roman += romanNumerals[10].to_s
				tens -= 1
			end
		elsif (tens == 4) #XL
			roman += romanNumerals[10].to_s
			roman += romanNumerals[50].to_s
		else #X..XXX
			while tens > 0 do
				roman += romanNumerals[10].to_s
				tens -= 1
			end
		end

		#Get the ones
		if (ones == 9) #IX
			roman += romanNumerals[1].to_s
			roman += romanNumerals[10].to_s
		elsif (ones >= 5) #V..VIII
			roman += romanNumerals[5].to_s
			ones -= 1
			while ones >= 5 do
				roman += romanNumerals[1].to_s
				ones -= 1
			end
		elsif (ones == 4) #IV
			roman += romanNumerals[1].to_s
			roman += romanNumerals[5].to_s
		else #I...III
			while ones > 0 do
				roman += romanNumerals[1].to_s
				ones -= 1
			end
		end

		return roman
	end
end
