class Integer

	module NumeralRomanConvertissor

		SPECIAL_VALUES = {
			1    => "I",
			4    => "IV",
			5    => "V",
			9    => "IX",
			10   => "X",
			40   => "XL",
			50   => "L",
			90   => "XC",
			100  => "C",
			400  => "CD",
			500  => "D",
			900  => "CM",
			1000 => "M"
		}.freeze

		def self.to_roman number
			for_each_special_values do |arab, roman|
				return roman + to_roman(number-arab) if number >= arab
			end
			return SPECIAL_VALUES[1] * number
		end
	
	private

		def self.for_each_special_values
			SPECIAL_VALUES.sort.reverse.each do |k|
				yield(k[0], k[1])
			end
		end
		
		def self.ordered_keys
			SPECIAL_VALUES.keys.reverse
		end

	end

	def to_roman
		NumeralRomanConvertissor.to_roman self
	end

end
