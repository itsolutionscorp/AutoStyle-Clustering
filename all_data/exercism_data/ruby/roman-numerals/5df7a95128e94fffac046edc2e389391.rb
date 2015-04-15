class Fixnum
	def to_roman
		nb = self.to_s
		rom = ""
		if nb.length >= 1
			rom = roman(nb[nb.length - 1], 'I', 'V', 'X')
		end
		if nb.length >= 2
			rom = roman(nb[nb.length - 2], 'X', 'L', 'C') << rom
		end
		if nb.length >= 3
			rom = roman(nb[nb.length - 3], 'C', 'D', 'M') << rom
		end
		if nb.length >= 4
			rom = roman(nb[nb.length - 4], 'M', '', '') << rom
		end
	return rom
	end

	def roman(c, one, five, ten)
		str = ""
		if c == '1'
			str << one
		elsif c == '2'
			str << one << one
		elsif c == '3'
			str << one << one << one
		elsif c == '4'
			str << one << five
		elsif c == '5'
			str << five
		elsif c == '6'
			str << five << one
		elsif c == '7'
			str << five << one << one
		elsif c == '8'
			str << five << one << one << one
		elsif c == '9'
			str << one << ten
		end
		return str
	end

end
