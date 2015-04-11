class Fixnum
	@@NUM_TO_ROMAN = {
		1000 => 'M',
		900  => 'CM',
		500  => 'D',
		400  => 'CD',
		100  => 'C',
		90   => 'XC',
		50   => 'L',
		40   => 'XL',
		10   => 'X',
		9    => 'IX',
		5    => 'V',
		4    => 'IV',
		3    => 'III',
		2    => 'II',
		1    => 'I'
	}

	def to_roman
		num = self
		raise ArgumentError, "shouldn't larger than 3000" if num > 3000

		@@NUM_TO_ROMAN.each_with_object('') do |(d, r), roman|
				num -= d and roman << r while num >= d
		end
	end

end
