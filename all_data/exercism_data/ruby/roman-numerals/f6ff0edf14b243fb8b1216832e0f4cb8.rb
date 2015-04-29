module RomanNumeral

	def to_roman
		if self > 3999
			to_s # Too big, so we just to_s the object
		else
			[
				get_thousands.to_roman_unit('','','M'),
				get_hundreds.to_roman_unit('M','D','C'),
				get_tens.to_roman_unit('C','L','X'),
				get_units.to_roman_unit('X','V','I'),
			].join
			
		end
	end

	def to_roman_unit(high, mid, low)
		case self
		when 9 then "#{low}#{high}"
		when 8 then "#{mid}#{low * 3}"
		when 7 then "#{mid}#{low * 2}"
		when 6 then "#{mid}#{low}"
		when 5 then "#{mid}"
		when 4 then "#{low}#{mid}"
		when 3 then low*3
		when 2 then low*2
		when 1 then low
		else ""
		end
	end

	def get_thousands
		self / 1000
	end

	def get_hundreds
		(self % 1000) / 100
	end

	def get_tens
		(self % 100) / 10
	end

	def get_units
		self % 10
	end
end

Numeric.prepend RomanNumeral
