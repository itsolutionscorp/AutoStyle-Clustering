class Fixnum
	def to_roman
		recursive_construction(self.to_i)
	end

	def recursive_construction(remaining_int)
		if (remaining_int / 1000) >= 1
			return 'M' + recursive_construction(remaining_int - 1000)
		elsif (remaining_int / 900) >= 1
			return 'CM' + recursive_construction(remaining_int - 900)
		elsif (remaining_int / 500) >= 1
			return 'D' + recursive_construction(remaining_int - 500)
		elsif (remaining_int / 400) >= 1
			return 'CD' + recursive_construction(remaining_int - 400)
		elsif (remaining_int / 100) >= 1
			return 'C' + recursive_construction(remaining_int - 100)
		elsif (remaining_int / 90)  >= 1
			return 'XC' + recursive_construction(remaining_int - 90)
		elsif (remaining_int / 50) >= 1
			return 'L' + recursive_construction(remaining_int - 50)
		elsif (remaining_int / 40) >= 1
			return 'XL' + recursive_construction(remaining_int - 40)
		elsif (remaining_int / 10) >= 1
			return 'X' + recursive_construction(remaining_int - 10)
		elsif (remaining_int / 9) >= 1
			return 'IX' + recursive_construction(remaining_int - 9)
		elsif (remaining_int / 5) >= 1
			return 'V' + recursive_construction(remaining_int - 5)
		elsif (remaining_int / 4) >= 1
			return 'IV' + recursive_construction(remaining_int - 4)
		elsif (remaining_int / 1) >= 1	
			return 'I' + recursive_construction(remaining_int - 1)
		else
			return ''
		end
	end
end
