class Fixnum
	
	def to_roman
		romans = {1=>"I", 5=>"V", 10=>"X", 50=>"L", 100=>"C", 500=>"D", 1000=>"M"}

		if romans[self]
			romans[self]
		else
			roman = ""
			vals = self.to_s.reverse.chars.to_a
			vals.length.times do |d| 
				puts d
				case d
					when 0
						symbols = ["I","V","X"]
					when 1
						symbols = ["X","L","C"]
					when 2
						symbols = ["C","D","M"]
					else
						symbols = ["M"]
				end
				roman = buildRoman(vals[d].to_i,symbols) + roman
			end
			return roman
		end
	end

	def buildRoman(digit, symbols)
		val = ""
		if (digit>=5) and (digit<9)
			val = symbols[1]
		else
			if digit == 9
				val = symbols[0] + symbols[2]
			end
			if digit == 4
				val = symbols[0] + symbols[1]
			end
		end
		if (digit%5 <= 3)
			(digit%5).times { val = val + symbols[0]} 
		end
		return val
	end

end
