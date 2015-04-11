class Integer

	PLACES_CONVERSION_TABLE = {
		'thousands' => {1 => 'M'},
		'hundreds' => {1 => 'C', 5 => 'D', 9 => 'CM'},
		'tens' => {1 => 'X', 5 => 'L', 9 => 'XC'},
		'ones' => {1 => 'I', 5 => 'V', 9 => 'IX'}}

	def to_roman
		raise "Error: to_roman can only handle integers between 1 and 3999" if ((self > 3999) || (self < 1))
		roman = ''

		PLACES_CONVERSION_TABLE.each do |place, conversion|
			number = self.send(place + '_place')
			case number
				when 1..3 then number.times { roman << conversion[1]}
				when 4 then roman << conversion[1] + conversion[5]
				when 5 then roman << conversion[5]
				when 6..8
					roman << conversion[5]
					(number - 5).times { roman << conversion[1]}
				when 9 then roman << conversion[9]
			end
		end
		roman
	end

	def thousands_place
		self / 1000
	end

	def hundreds_place
		(self % 1000) / 100
	end

	def tens_place
		(self % 100) / 10
	end

	def ones_place
		self % 10
	end

end
