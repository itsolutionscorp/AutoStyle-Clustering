class Integer
	def to_roman
		number = self

		symbols = {"M" => 1000,
	     	           "D" => 500,
			   "C" => 100,
			   "L" => 50,
			   "X" => 10,
			   "V" => 5,
			   "I" => 1}

		substitutions = {"DCCCC" => "CM",
		                 "CCCC"  => "CD",
				 "LXXXX" => "XC",
				 "XXXX"  => "XL",
				 "VIIII" => "IX",
				 "IIII"  => "IV"}

		representation = symbols.map { |symbol, value|
			multiplier = (number/value).truncate
			number -= multiplier*value
			symbol*multiplier
		}.join("")

		substitutions.each { |pattern, substitution|
			representation.gsub!(pattern, substitution)
		}

		representation
	end
end
