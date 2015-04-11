class Integer
	@@NUMERALS = {1=>'I', 5=>'V', 10=>'X', 50=>'L', 100=>'C', 500=>'D', 1000=>'M'}

	# IX, etc.
	@@VALID_PAIRS = %W{IV IX XL XC CD CM}

	@@VALID_PAIRS.each do |pair|
		@@NUMERALS[ @@NUMERALS.invert[pair.chars.to_a[1]] - @@NUMERALS.invert[pair.chars.to_a[0]] ] = pair
	end

	def to_roman
		string = ''
		number = self

		for value in @@NUMERALS.keys.sort.reverse
			count, number = number.divmod(value)
  			string << (@@NUMERALS[value] * count)
		end

		return string
	end
end
